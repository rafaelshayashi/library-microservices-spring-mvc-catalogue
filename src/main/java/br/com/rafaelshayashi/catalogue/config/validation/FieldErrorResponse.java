package br.com.rafaelshayashi.catalogue.config.validation;

import br.com.rafaelshayashi.catalogue.exception.ResourceAlreadyExistsException;

public class FieldErrorResponse {

    private final String field;
    private final String reference;
    private final String error;

    public FieldErrorResponse(String field, String reference, String error) {
        this.field = field;
        this.reference = reference;
        this.error = error;
    }

    public static FieldErrorResponse from(RuntimeException exception) {
        if(exception instanceof ResourceAlreadyExistsException){
            ResourceAlreadyExistsException ex = (ResourceAlreadyExistsException) exception;
            return new FieldErrorResponse(ex.getPrimaryKeyFieldName(), ex.getReferenceUuid(), ex.getMessage());
        }
        return new FieldErrorResponse("default", "default", exception.getMessage());
    }

    public String getField() {
        return field;
    }

    public String getReference() {
        return reference;
    }

    public String getError() {
        return error;
    }
}
