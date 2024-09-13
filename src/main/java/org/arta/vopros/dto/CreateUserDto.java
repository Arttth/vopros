package org.arta.vopros.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateUserDto {
    String nickname;
    String name;
    String lastname;
    String dateOfBirth;
    String profilePhoto;
    String email;
    String password;
}
