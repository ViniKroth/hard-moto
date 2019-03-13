package br.com.hardmoto.unit.validator;

import br.com.hardmoto.api.v1.dto.CityInput;
import br.com.hardmoto.api.v1.dto.validator.InputValidator;
import br.com.hardmoto.exception.ValidationException;
import org.junit.Test;

import java.math.BigDecimal;

public class InputValidatorTest {

    @Test
    public void shouldValidate() throws ValidationException {
        InputValidator.validate(buildValidCityInput());
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToNullBody() throws ValidationException {
        InputValidator.validate(null);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToInvalidId() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setId(-1L);
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToInvalidName() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setName("");
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToInvalidOperation() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setOperation("TEST");
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToInvalidDistance() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setDistance(BigDecimal.valueOf(-1));
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToInvalidDestinationId() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setDestinationId(-1L);
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToNullId() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setId(null);
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToNullName() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setName(null);
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToNullOperation() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setOperation(null);
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToNullDistance() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setDistance(null);
        InputValidator.validate(cityInput);
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowValidationExceptionDueToNullDestinationId() throws ValidationException {
        CityInput cityInput = buildValidCityInput();
        cityInput.setDestinationId(null);
        InputValidator.validate(cityInput);
    }
    private CityInput buildValidCityInput() {
        CityInput cityInput = new CityInput();
        cityInput.setId(1L);
        cityInput.setDestinationId(2L);
        cityInput.setName("Test");
        cityInput.setOperation("ADD");
        cityInput.setDistance(BigDecimal.ONE);
        return cityInput;
    }
}
