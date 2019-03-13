package br.com.hardmoto.mapper;

import br.com.hardmoto.entity.CityEntity;
import br.com.hardmoto.entity.CityEntityPrimaryKey;
import br.com.hardmoto.model.City;

public class CityMapper {

    public CityEntity mapCityToCityEntity(City city) {
        CityEntity cityEntity = new CityEntity();
        CityEntityPrimaryKey cityEntityPrimaryKey = new CityEntityPrimaryKey(city.getId(), city.getDestinationId());

        cityEntity.setCityEntityPrimaryKey(cityEntityPrimaryKey);
        cityEntity.setName(city.getName());
        cityEntity.setDistance(city.getDistance());

        return cityEntity;
    }
}
