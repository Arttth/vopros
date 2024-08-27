package org.arta.vopros.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);

}
