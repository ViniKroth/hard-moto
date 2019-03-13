package br.com.hardmoto.entity;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;

@Table("city")
public class CityEntity {

    @PrimaryKey
    private CityEntityPrimaryKey cityEntityPrimaryKey;
    @Column(value = "name")
    private String name;
    @Column(value = "distance")
    private BigDecimal distance;

    public CityEntity(CityEntityPrimaryKey cityEntityPrimaryKey, String name, BigDecimal distance) {
        this.cityEntityPrimaryKey = cityEntityPrimaryKey;
        this.name = name;
        this.distance = distance;

    }

    public CityEntity() {
    }

    public CityEntityPrimaryKey getCityEntityPrimaryKey() {
        return cityEntityPrimaryKey;
    }

    public void setCityEntityPrimaryKey(CityEntityPrimaryKey cityEntityPrimaryKey) {
        this.cityEntityPrimaryKey = cityEntityPrimaryKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }


}
