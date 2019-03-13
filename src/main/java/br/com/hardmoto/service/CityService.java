package br.com.hardmoto.service;

import br.com.hardmoto.api.v1.dto.CityOutput;
import br.com.hardmoto.api.v1.dto.PathOutput;
import br.com.hardmoto.entity.CityEntity;
import br.com.hardmoto.entity.CityEntityPrimaryKey;
import br.com.hardmoto.exception.BusinessRuleException;
import br.com.hardmoto.exception.PersistenceException;
import br.com.hardmoto.exception.UnexpectedDatabaseException;
import br.com.hardmoto.mapper.CityMapper;
import br.com.hardmoto.model.City;
import br.com.hardmoto.repository.CityRepository;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Logger logger;

    @Autowired
    private CityMapper cityMapper;

    public PathOutput findShortestRoute(Long originCityId, Long cityDestinationId) throws BusinessRuleException {
        List<CityEntity> cities = cityRepository.findAll();

        HipsterDirectedGraph<Long, BigDecimal> graph = buildGraph(cities);
        SearchProblem searchProblem = GraphSearchProblem
                .startingFrom(originCityId)
                .in(graph)
                .takeCostsFromEdges()
                .build();

        List<String> idPath = Hipster.createDijkstra(searchProblem).search(cityDestinationId).getOptimalPaths();

        if (!validatePath(idPath, originCityId, cityDestinationId))
            throw new BusinessRuleException("The given id's DO NOT form a valid path.");

        return new PathOutput(idPath);
    }

    public CityOutput insert(City city) throws PersistenceException, BusinessRuleException {
        CityEntityPrimaryKey cityEntityPrimaryKey = new CityEntityPrimaryKey(city.getId(), city.getDestinationId());

        if (validateIfEntityExists(cityEntityPrimaryKey)) {
            logger.log(Level.SEVERE, String.format("[CREATE] Error inserting city with id: %d, and destination id: %d." +
                    " due to the fact that a city with same key-set already exists", city.getId(), city.getDestinationId()));
            throw new BusinessRuleException("A city with this key-set already exists in the database");
        }

        try {
            cityRepository.insert(cityMapper.mapCityToCityEntity(city));
            logger.log(Level.INFO, String.format("[CREATE]- City with id: %d with destination to id: %d has been successfully" +
                    " inserted.", city.getId(), city.getDestinationId()));
            return modelMapper.map(city, CityOutput.class);

        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("[CREATE] Error inserting city with id: %d, and destination id: %d." +
                    "Error: %s", city.getId(), city.getDestinationId(), e.getMessage()));
            throw new PersistenceException(e.getMessage());
        }
    }

    public CityOutput update(City city) throws PersistenceException {
        CityEntityPrimaryKey cityEntityPrimaryKey = new CityEntityPrimaryKey(city.getId(), city.getDestinationId());

        if (!validateIfEntityExists(cityEntityPrimaryKey)) {
            logger.log(Level.SEVERE, String.format("[UPDATE] Error updating city with id: %d, and destination id: %d." +
                    " due to the fact that a city with this key-set doesn't exist.", city.getId(), city.getDestinationId()));
            throw new NoSuchElementException("A city with this key-set DOESN'T exist in the database.");
        }

        try {
            cityRepository.save(cityMapper.mapCityToCityEntity(city));
            logger.log(Level.INFO, String.format("[UPDATE]- City with id: %d with destination to id: %d has been successfully" +
                    " updated.", city.getId(), city.getDestinationId()));
            return modelMapper.map(city, CityOutput.class);

        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("[UPDATE] Error updating city with id: %d, and destination id: %d." +
                    "Error: %s", city.getId(), city.getDestinationId(), e.getMessage()));
            throw new PersistenceException(e.getMessage());
        }
    }

    public void delete(City city) throws PersistenceException {
        try {
            if (!cityRepository.deleteCityIfExists(BigInteger.valueOf(city.getId()), BigInteger.valueOf(city.getDestinationId())))
                throw new NoSuchElementException("The city DOES NOT exist at the database, therefore, was not deleted.");

            logger.log(Level.INFO, String.format("[DELETE]- City with id: %d with destination to id: %d has been successfully" +
                    " deleted.", city.getId(), city.getDestinationId()));

        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("[DELETE] Error deleting city with id: %d, and destination id: %d." +
                    "Error: %s", city.getId(), city.getDestinationId(), e.getMessage()));
            throw new PersistenceException(e);
        }
    }

    public void checkDatabaseHealth() throws UnexpectedDatabaseException {
        try {
            cityRepository.healthCheckQuery();
        } catch (Exception e) {
            logger.log(Level.SEVERE, String.format("[HEALTH-CHECK] Health check failed due to the following error: %s.",
                    e.getMessage()));
            throw new UnexpectedDatabaseException(e.getMessage());
        }

    }

    private HipsterDirectedGraph buildGraph(List<CityEntity> cityEntities) {
        GraphBuilder<Long, BigDecimal> graphBuilder = GraphBuilder.create();

        cityEntities.stream().forEach(cityEntity -> graphBuilder.connect(cityEntity.getCityEntityPrimaryKey().getId())
                .to(cityEntity.getCityEntityPrimaryKey().getDestinationId()));

        return graphBuilder.createDirectedGraph();
    }

    private Boolean validateIfEntityExists(CityEntityPrimaryKey key) {
        return cityRepository.findById(key).isPresent();
    }

    private Boolean validatePath(List<?> path, Long originId, Long destinationId) {

        String idPath = path.toString().replaceAll("\\[|\\]", "");
        return idPath.startsWith(originId.toString()) && idPath.endsWith(destinationId.toString())
                && !(originId == destinationId);
    }
}
