package br.com.hardmoto.api.v1;

import br.com.hardmoto.api.v1.dto.CityInput;
import br.com.hardmoto.api.v1.dto.validator.InputValidator;
import br.com.hardmoto.exception.BusinessRuleException;
import br.com.hardmoto.exception.ValidationException;
import br.com.hardmoto.model.City;
import br.com.hardmoto.response.ApiResponse;
import br.com.hardmoto.service.CityService;
import br.com.hardmoto.singleton.AppContextSingleton;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("router")
public class HardMotoApi {

    private final ApplicationContext appContext = AppContextSingleton.getInstance();
    private final CityService cityService = (CityService) appContext.getBean("cityService");
    private final Logger logger = (Logger) appContext.getBean("logger");
    private final ModelMapper modelMapper = (ModelMapper) appContext.getBean("modelMapper");
    public static final String ADD = "ADD";
    public static final String UPT= "UPT";
    public static final String DEL = "DEL";


    @Path("/health")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        try {
            cityService.checkDatabaseHealth();
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("/city")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response operationRouter(List<CityInput> citiesInput) {
        logger.log(Level.INFO, String.format("Input object(s) received: %s ", citiesInput.toString()));
        List<ApiResponse> apiResponses = new ArrayList<>();

        citiesInput.stream().forEach(cityInput -> {
            try {
                InputValidator.validate(cityInput);
            } catch (ValidationException e) {
                logger.log(Level.SEVERE, String.format("City of id: %d and destination_id: %d with malformed body. " +
                        "Error: %s.", cityInput.getId(), cityInput.getDestinationId(), e.getMessage()));

                apiResponses.add(new ApiResponse(Status.BAD_REQUEST.getReasonPhrase(),
                        Status.BAD_REQUEST.getStatusCode(), e.getMessage()));
                return;
            }

            City city = modelMapper.map(cityInput, City.class);
            String operation = cityInput.getOperation();

            switch (operation) {
                case ADD:
                    add(city, apiResponses);
                    break;
                case UPT:
                    update(city, apiResponses);
                    break;
                case DEL:
                    delete(city, apiResponses);
                    break;
            }
        });

        return Response.ok(new Gson().toJson(apiResponses)).build();
    }


    @Path("/city/shortest/{id}/to/{dest}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findShortestPath(@PathParam("id") Long id, @PathParam("dest") Long destinationId) {
        try {
            return Response.ok(cityService.findShortestRoute(id, destinationId).toString()).build();
        } catch (BusinessRuleException e) {
            return Response.status(Status.EXPECTATION_FAILED).entity(e.getMessage()).build();
        }
    }

    private void add(City city, List<ApiResponse> apiResponses) {
        try {
            apiResponses.add(new ApiResponse(ADD, cityService.insert(city), Status.CREATED.getReasonPhrase(),
                    Status.CREATED.getStatusCode()));
        } catch (Exception e) {
            apiResponses.add(new ApiResponse(ADD, Status.EXPECTATION_FAILED.getReasonPhrase(),
                    Status.EXPECTATION_FAILED.getStatusCode(), e.getMessage()));
        }
    }

    private void update(City city, List<ApiResponse> apiResponses) {
        try {
            apiResponses.add(new ApiResponse(UPT, cityService.update(city), Status.ACCEPTED.getReasonPhrase(),
                    Status.ACCEPTED.getStatusCode()));
        } catch (Exception e) {
            apiResponses.add(new ApiResponse(UPT, Status.EXPECTATION_FAILED.getReasonPhrase(),
                    Status.EXPECTATION_FAILED.getStatusCode(), e.getMessage()));
        }
    }

    private void delete(City city, List<ApiResponse> apiResponses) {
        try {
            cityService.delete(city);
            apiResponses.add(new ApiResponse(DEL, Status.OK.getReasonPhrase(),
                    Status.ACCEPTED.getStatusCode()));
        } catch (Exception e) {
            apiResponses.add(new ApiResponse("DEL", Status.EXPECTATION_FAILED.getReasonPhrase(),
                    Status.EXPECTATION_FAILED.getStatusCode(), e.getMessage()));
        }
    }
}

