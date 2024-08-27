package org.arta.vopros.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.arta.vopros.domain.Role;
import org.arta.vopros.domain.User;
import org.arta.vopros.dto.CreateUserDto;
import org.arta.vopros.utils.LocalDateFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    @Override
    public User mapFrom(CreateUserDto object) throws ParseException {
        return User.builder()
                .name(object.getName())
                .lastname(object.getLastname())
                .nickname(object.getNickname())
                .dateOfBirth(LocalDateFormatter.format(object.getDateOfBirth()))
                .email(object.getEmail())
                .password(object.getPassword())
                .reputation(0)
                .role(Role.USER)
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}
