package org.arta.vopros.validator;

import lombok.NoArgsConstructor;
import org.arta.vopros.dto.CreateUserDto;
import org.arta.vopros.utils.LocalDateFormatter;


@NoArgsConstructor
public class CreateUserValidator implements Validator<CreateUserDto>{
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    public ValidationResult isValid(CreateUserDto createUserDto) {
        ValidationResult validationResult = new ValidationResult();
        if (createUserDto.getNickname().isEmpty()) {
            validationResult.add(Error.of("invalid.nickname", "Nickname is invalid"));
        }
        if (createUserDto.getName().isEmpty()) {
            validationResult.add(Error.of("invalid.name", "Name is invalid"));
        }
        if (createUserDto.getLastname().isEmpty()) {
            validationResult.add(Error.of("invalid.lastname", "Lastname is invalid"));
        }
        if (!LocalDateFormatter.isValid(createUserDto.getDateOfBirth())) {
            validationResult.add(Error.of("invalid.birthday", "Birthday is invalid"));
        }
        // TODO: email validation
        if (createUserDto.getEmail().isEmpty()) {
            validationResult.add(Error.of("invalid.email", "Email is invalid"));
        }
        if (createUserDto.getPassword().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is invalid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
