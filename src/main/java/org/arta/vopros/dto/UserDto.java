package org.arta.vopros.dto;

import lombok.Builder;
import lombok.Value;
import org.arta.vopros.domain.Role;

import java.time.LocalDate;
@Builder
public record UserDto(Long userId, String nickname, String name, String lastname, LocalDate birthday, String profilePhoto, Integer reputation,
                      String email, Role role) {
}
