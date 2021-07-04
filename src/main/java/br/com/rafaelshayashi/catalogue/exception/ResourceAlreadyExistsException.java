package br.com.rafaelshayashi.catalogue.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    private final String primaryKeyFieldName;
    private final String referenceUuid;
    private final String message;

    public ResourceAlreadyExistsException(String primaryKeyFieldName, String referenceUuid, String message) {
        this.primaryKeyFieldName = primaryKeyFieldName;
        this.referenceUuid = referenceUuid;
        this.message = message;
    }

    public String getPrimaryKeyFieldName() {
        return primaryKeyFieldName;
    }

    public String getReferenceUuid() {
        return referenceUuid;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
