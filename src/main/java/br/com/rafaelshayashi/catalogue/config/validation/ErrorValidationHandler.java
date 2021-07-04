package br.com.rafaelshayashi.catalogue.config.validation;

import br.com.rafaelshayashi.catalogue.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorValidationHandler {

    private final MessageSource messageSource;

    @Autowired
    public ErrorValidationHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessageResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<FieldErrorResponse> fieldErrorResponses = fieldErrors.stream().map(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            return new FieldErrorResponse(fieldError.getField(), "field", message);
        }).collect(Collectors.toList());

        return ErrorMessageResponse.create("Validation error", fieldErrorResponses);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ErrorMessageResponse handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        return ErrorMessageResponse.create("The resource already Exists", FieldErrorResponse.from(exception));
    }
}
