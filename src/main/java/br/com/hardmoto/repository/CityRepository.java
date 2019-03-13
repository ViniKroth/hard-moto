package br.com.hardmoto.repository;

import br.com.hardmoto.entity.CityEntity;
import br.com.hardmoto.entity.CityEntityPrimaryKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CityRepository extends CassandraRepository<CityEntity, CityEntityPrimaryKey> {

    @Query("SELECT now() FROM system.local")
    public Object[] healthCheckQuery();

    @Query(value = "DELETE FROM city WHERE id = ?0 AND destination_id = ?1 IF EXISTS" )
    public Boolean deleteCityIfExists(BigInteger id, BigInteger destination_id);
}
