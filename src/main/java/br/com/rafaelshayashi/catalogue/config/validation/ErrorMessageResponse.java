package br.com.rafaelshayashi.catalogue.config.validation;

import java.util.Collections;
import java.util.List;

public class ErrorMessageResponse {

    private String message;
    private List<FieldErrorResponse> errors;

    public ErrorMessageResponse(String message, List<FieldErrorResponse> errors) {
        this.message = message;
        this.errors = errors;
    }

    public static ErrorMessageResponse create(String message, FieldErrorResponse fieldErrorResponse) {
        return new ErrorMessageResponse(message, Collections.singletonList(fieldErrorResponse));
    }

    public static ErrorMessageResponse create(String message, List<FieldErrorResponse> fieldErrorResponse) {
        return new ErrorMessageResponse(message, fieldErrorResponse);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorResponse> errors) {
        this.errors = errors;
    }
}
