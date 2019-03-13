package br.com.hardmoto.api.v1.dto.validator;

import br.com.hardmoto.api.v1.dto.CityInput;
import br.com.hardmoto.exception.ValidationException;

public class InputValidator {

    public static void validate(CityInput cityInput) throws ValidationException {
        if (cityInput == null) {
            throw new ValidationException("The provided body MUST cannot be null.");
        }
        if (cityInput.getId() == null || cityInput.getId() <= 0)
            throw new ValidationException("The provided id MUST be a positive natural number.");

        if (cityInput.getName() == null || cityInput.getName().isEmpty())
            throw new ValidationException("The provided name CANNOT be null or empty");

        if (cityInput.getOperation() == null || cityInput.getOperation().length() != 3)
            throw new ValidationException("Invalid operation selected. Please choose a valid one.");

        if (cityInput.getDistance() == null || cityInput.getDistance().intValue() <= 0)
            throw new ValidationException("The provided distance MUST be a positive number.");

        if (cityInput.getDestinationId() == null || cityInput.getDestinationId() <= 0)
            throw new ValidationException("The provided to_id MUST be a positive natural number.");

    }
}
