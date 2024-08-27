package org.arta.vopros.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String name;
    private String lastname;
    private LocalDate dateOfBirth;
    private String profilePhoto;
    private Integer reputation;
    private String email;
    private String password;
    private Role role;
}
