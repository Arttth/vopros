package org.arta.vopros.exception;

import lombok.Getter;
import org.arta.vopros.validator.Error;

import java.util.List;

public class ValidationException extends RuntimeException{
    @Getter
    private final List<Error> errors;
    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
