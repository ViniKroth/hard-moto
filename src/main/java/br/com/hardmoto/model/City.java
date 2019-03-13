package br.com.hardmoto.model;

import java.math.BigDecimal;

public class City {

    private Long id;
    private String name;
    private BigDecimal distance;
    private Long destinationId;

    public City(Long id, String name, BigDecimal distance, Long destinationId) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.destinationId = destinationId;
    }

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }
}
