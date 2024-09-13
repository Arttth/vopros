package org.arta.vopros.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.arta.vopros.domain.User;
import org.arta.vopros.dto.UserDto;

import java.text.ParseException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<User, UserDto> {
    private static final UserMapper INSTANCE = new UserMapper();
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .userId(object.getId())
                .nickname(object.getNickname())
                .name(object.getName())
                .lastname(object.getLastname())
                .birthday(object.getDateOfBirth())
                .reputation(object.getReputation())
                .email(object.getEmail())
                .profilePhoto(object.getProfilePhoto())
                .role((object.getRole()))
                .build();
    }

    public static UserMapper getInstance() {
        return INSTANCE;
    }
}
