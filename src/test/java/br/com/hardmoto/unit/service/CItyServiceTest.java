package br.com.hardmoto.unit.service;

import br.com.hardmoto.api.v1.dto.CityOutput;
import br.com.hardmoto.entity.CityEntity;
import br.com.hardmoto.entity.CityEntityPrimaryKey;
import br.com.hardmoto.exception.BusinessRuleException;
import br.com.hardmoto.exception.PersistenceException;
import br.com.hardmoto.exception.UnexpectedDatabaseException;
import br.com.hardmoto.mapper.CityMapper;
import br.com.hardmoto.model.City;
import br.com.hardmoto.repository.CityRepository;
import br.com.hardmoto.service.CityService;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.data.cassandra.CassandraInvalidQueryException;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CItyServiceTest {

    @InjectMocks
    private CityService cityService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Logger logger;

    @Mock
    private CityMapper cityMapper;


    @Test
    public void shouldFindShortestRoute() throws BusinessRuleException {
        List<CityEntity> cityEntities = EnhancedRandom.randomListOf(2, CityEntity.class);
        CityEntityPrimaryKey cityEntityPrimaryKey = new CityEntityPrimaryKey(1L, cityEntities.get(1)
                .getCityEntityPrimaryKey().getId());
        cityEntities.get(0).setCityEntityPrimaryKey(cityEntityPrimaryKey);
        when(cityRepository.findAll()).thenReturn(cityEntities);

        cityService.findShortestRoute(cityEntities.get(0).getCityEntityPrimaryKey().getId(),
                cityEntities.get(1).getCityEntityPrimaryKey().getId());
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThrowBusinessRuleExceptionWhenFindingShortestPath() throws BusinessRuleException {
        List<CityEntity> cityEntities = EnhancedRandom.randomListOf(2, CityEntity.class);
        when(cityRepository.findAll()).thenReturn(cityEntities);

        cityService.findShortestRoute(cityEntities.get(0).getCityEntityPrimaryKey().getId(),
                cityEntities.get(1).getCityEntityPrimaryKey().getId());
    }

    @Test
    public void shouldInsertCity() throws BusinessRuleException, PersistenceException {
        City city = EnhancedRandom.random(City.class);
        CityOutput cityOutput = EnhancedRandom.random(CityOutput.class);

        when(cityRepository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(logger).log(any(Level.class), any(String.class));
        when(modelMapper.map(any(City.class), eq(CityOutput.class))).thenReturn(cityOutput);
        assertEquals(cityOutput.toString(), cityService.insert(city).toString());
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThrowBusinessRuleExceptionWhenInsertingCity() throws BusinessRuleException, PersistenceException {
        City city = EnhancedRandom.random(City.class);
        CityEntity cityEntity = EnhancedRandom.random(CityEntity.class);

        when(cityRepository.findById(any())).thenReturn(Optional.of(cityEntity));
        doNothing().when(logger).log(any(Level.class), any(String.class));
        cityService.insert(city).toString();
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test(expected = PersistenceException.class)
    public void shouldThrowṔersistenceExceptionWhenInsertingCity() throws BusinessRuleException, PersistenceException {
        City city = EnhancedRandom.random(City.class);
        CityEntity cityEntity = EnhancedRandom.random(CityEntity.class);
        CityOutput cityOutput = EnhancedRandom.random(CityOutput.class);

        when(cityRepository.findById(any())).thenReturn(Optional.empty());
        when(cityMapper.mapCityToCityEntity(any(City.class))).thenReturn(cityEntity);
        when(cityRepository.insert(any(CityEntity.class))).thenThrow(new CassandraInvalidQueryException("Invalid query"));
        doNothing().when(logger).log(any(Level.class), any(String.class));
        assertEquals(cityOutput.toString(), cityService.insert(city).toString());
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test
    public void shouldUpdateCity() throws PersistenceException {
        City city = EnhancedRandom.random(City.class);
        CityEntity cityEntity = EnhancedRandom.random(CityEntity.class);
        CityOutput cityOutput = EnhancedRandom.random(CityOutput.class);

        when(cityRepository.findById(any())).thenReturn(Optional.of(cityEntity));
        doNothing().when(logger).log(any(Level.class), any(String.class));
        when(modelMapper.map(any(City.class), eq(CityOutput.class))).thenReturn(cityOutput);
        assertEquals(cityOutput.toString(), cityService.update(city).toString());
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenUpdatingCity()throws PersistenceException {
        City city = EnhancedRandom.random(City.class);

        when(cityRepository.findById(any())).thenReturn(Optional.empty());
        doNothing().when(logger).log(any(Level.class), any(String.class));
        cityService.update(city).toString();
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test(expected = PersistenceException.class)
    public void shouldThrowṔersistenceExceptionWhenUpdatingCity() throws BusinessRuleException, PersistenceException {
        City city = EnhancedRandom.random(City.class);
        CityEntity cityEntity = EnhancedRandom.random(CityEntity.class);

        when(cityRepository.findById(any())).thenReturn(Optional.of(cityEntity));
        when(cityMapper.mapCityToCityEntity(any(City.class))).thenReturn(cityEntity);
        when(cityRepository.save(any(CityEntity.class))).thenThrow(new CassandraInvalidQueryException("Invalid query"));
        doNothing().when(logger).log(any(Level.class), any(String.class));
        cityService.update(city);
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test
    public void shouldDeleteCity()throws PersistenceException {
        City city = EnhancedRandom.random(City.class);
        when(cityRepository.deleteCityIfExists(any(BigInteger.class), any(BigInteger.class))).thenReturn(true);
        doNothing().when(logger).log(any(Level.class), any(String.class));
        cityService.delete(city);
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test(expected = PersistenceException.class)
    public void shouldThrowPersistenceExceptionWhenDeletingCityAndCityDoeNotExist() throws PersistenceException {
        City city = EnhancedRandom.random(City.class);
        when(cityRepository.deleteCityIfExists(any(BigInteger.class), any(BigInteger.class))).thenReturn(false);
        doNothing().when(logger).log(any(Level.class), any(String.class));
        cityService.delete(city);
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test(expected = PersistenceException.class)
    public void shouldThrowPersistenceExceptionWhenDeletingCity() throws PersistenceException {
        City city = EnhancedRandom.random(City.class);
        when(cityRepository.deleteCityIfExists(any(BigInteger.class), any(BigInteger.class)))
                .thenThrow(new CassandraInvalidQueryException("Invalid query"));
        doNothing().when(logger).log(any(Level.class), any(String.class));
        cityService.delete(city);
        verify(logger).log(any(Level.class), any(String.class));
    }

    @Test
    public void shouldCheckDatabaseHealth() throws UnexpectedDatabaseException {
        cityService.checkDatabaseHealth();
    }

    @Test(expected = UnexpectedDatabaseException.class)
    public void shouldThrowUnexpectedDatabaseException() throws UnexpectedDatabaseException{
        when(cityRepository.healthCheckQuery()).thenThrow(new CassandraInvalidQueryException("Invalid query"));
        doNothing().when(logger).log(any(Level.class), any(String.class));
        cityService.checkDatabaseHealth();
        verify(logger).log(any(Level.class), any(String.class));
    }
}
