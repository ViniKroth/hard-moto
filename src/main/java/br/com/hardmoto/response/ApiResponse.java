package br.com.hardmoto.response;

import br.com.hardmoto.api.v1.dto.CityOutput;

public class ApiResponse {

    private String operation;
    private CityOutput cityOutput;
    private String appropriateHttpStatus;
    private Integer appropriateHttStatusCode;
    private String errorMessage;


    public ApiResponse(String operation, CityOutput cityOutput, String appropriateHttpStatus, Integer appropriateHttStatusCode) {
        this.operation = operation;
        this.cityOutput = cityOutput;
        this.appropriateHttpStatus = appropriateHttpStatus;
        this.appropriateHttStatusCode = appropriateHttStatusCode;
    }

    public ApiResponse(String operation, String appropriateHttpStatus, Integer appropriateHttStatusCode, String errorMessage) {
        this.operation = operation;
        this.appropriateHttpStatus = appropriateHttpStatus;
        this.appropriateHttStatusCode = appropriateHttStatusCode;
        this.errorMessage = errorMessage;
    }

    public ApiResponse(String operation, String appropriateHttpStatus, Integer appropriateHttStatusCode) {
        this.operation = operation;
        this.appropriateHttpStatus = appropriateHttpStatus;
        this.appropriateHttStatusCode = appropriateHttStatusCode;
    }

    public ApiResponse(String appropriateHttpStatus, Integer appropriateHttStatusCode, String errorMessage) {
        this.appropriateHttpStatus = appropriateHttpStatus;
        this.appropriateHttStatusCode = appropriateHttStatusCode;
        this.errorMessage = errorMessage;
    }

    public ApiResponse() {
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public CityOutput getCityOutput() {
        return cityOutput;
    }

    public void setCityOutput(CityOutput cityOutput) {
        this.cityOutput = cityOutput;
    }

    public String getAppropriateHttpStatus() {
        return appropriateHttpStatus;
    }

    public void setAppropriateHttpStatus(String appropriateHttpStatus) {
        this.appropriateHttpStatus = appropriateHttpStatus;
    }

    public Integer getAppropriateHttStatusCode() {
        return appropriateHttStatusCode;
    }

    public void setAppropriateHttStatusCode(Integer appropriateHttStatusCode) {
        this.appropriateHttStatusCode = appropriateHttStatusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
