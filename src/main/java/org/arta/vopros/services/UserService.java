package org.arta.vopros.services;

import org.arta.vopros.db.impl.UserDao;
import org.arta.vopros.domain.User;
import org.arta.vopros.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final UserDao userDao = UserDao.getInstance();
    public List<UserDto> findAll() {
        return userDao.findAll().stream().map(user ->
                new UserDto(user.getId(), "%s, %s %s, (%s)"
                        .formatted(
                                user.getNickname(),
                                user.getName(),
                                user.getLastname(),
                                user.getDateOfBirth()
                        ))).collect(Collectors.toList());
    }

    private UserService() {}
    public static UserService getInstance() {
        return INSTANCE;
    }
}
