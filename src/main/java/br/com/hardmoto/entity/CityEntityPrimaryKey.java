package br.com.hardmoto.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CityEntityPrimaryKey {
    @PrimaryKeyColumn(value = "id")
    Long id;
    @PrimaryKeyColumn(name = "destination_id", type = PrimaryKeyType.PARTITIONED)
    Long destinationId;


    public CityEntityPrimaryKey(Long id, Long destinationId) {
        this.id = id;
        this.destinationId = destinationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }
}
