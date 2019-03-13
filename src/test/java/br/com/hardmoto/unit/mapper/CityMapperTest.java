package br.com.hardmoto.unit.mapper;

import br.com.hardmoto.entity.CityEntity;
import br.com.hardmoto.mapper.CityMapper;
import br.com.hardmoto.model.City;
import io.github.benas.randombeans.api.EnhancedRandom;

import org.junit.Test;

import static org.junit.Assert.*;
public class CityMapperTest {

    private final CityMapper cityMapper = new CityMapper();

    @Test
    public void shouldMapCityToCityEntity(){
        City city = EnhancedRandom.random(City.class);
        CityEntity cityEntity = cityMapper.mapCityToCityEntity(city);
        assertEquals(city.getId(), cityEntity.getCityEntityPrimaryKey().getId());
        assertEquals(city.getDestinationId(), cityEntity.getCityEntityPrimaryKey().getDestinationId());
        assertEquals(city.getName(), cityEntity.getName());
        assertEquals(city.getDistance(), cityEntity.getDistance());
    }
}
