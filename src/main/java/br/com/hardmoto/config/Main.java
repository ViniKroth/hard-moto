package br.com.hardmoto.config;

import br.com.hardmoto.model.City;
import br.com.hardmoto.repository.CityRepository;
import br.com.hardmoto.service.CityService;
import br.com.hardmoto.singleton.AppContextSingleton;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;


public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = AppContextSingleton.getInstance();
        CityRepository cityRepository = (CityRepository) applicationContext.getBean("cityRepository");
        CityService cityService = (CityService) applicationContext.getBean("cityService");
        ModelMapper modelMapper = (ModelMapper) applicationContext.getBean("modelMapper");


//
//        System.out.println(cityService.insert(new City(5L, "TESTE1",  BigDecimal.valueOf(30), 2L)).toString());
//        System.out.println(cityService.insert(new City(1L, "TESTE1",  BigDecimal.valueOf(30), 2L)).toString());
//        System.out.println(cityService.insert(new City(1L, "TESTE2",  BigDecimal.valueOf(30), 3L)).toString());
//        System.out.println(cityService.insert(new City(2L, "TESTE3",  BigDecimal.valueOf(50), 3L)).toString());
//        System.out.println(cityService.insert(new City(3L, "TESTE4",  BigDecimal.valueOf(10), 4L)).toString());
//          System.out.println(cityService.insert(new City(5L, "TESTE2",  BigDecimal.valueOf(10), 4L)).toString());
//        System.out.println(cityService.insert(new City(1L, "TESTE2",  BigDecimal.valueOf(10), 7L)).toString());
//        System.out.println(cityService.insert(new City(7L, "TESTE2",  BigDecimal.valueOf(1), 4L)).toString());

        //   cityRepository.deleteCityIfExists(BigInteger.valueOf(5), BigInteger.valueOf(4));
        //System.out.println(cityService.update(new City(22L, "teste",  BigDecimal.valueOf(50), 2L)).toString());
//        cityService.delete(new City(7L, "TESTE1",  BigDecimal.valueOf(30), 4L));
//        cityService.delete(new City(1L, "TESTE1",  BigDecimal.valueOf(30), 3L));
//        cityService.delete(new City(2L, "TESTE1",  BigDecimal.valueOf(30), 3L));
        System.out.println(cityService.findShortestRoute(1L, 2L).toString());

    }


}
