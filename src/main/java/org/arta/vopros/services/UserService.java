package org.arta.vopros.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.arta.vopros.db.impl.UserDao;
import org.arta.vopros.dto.CreateUserDto;
import org.arta.vopros.dto.UserDto;
import org.arta.vopros.mapper.CreateUserMapper;
import org.arta.vopros.mapper.UserMapper;
import org.arta.vopros.validator.CreateUserValidator;
import org.arta.vopros.exception.ValidationException;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final UserDao userDao = UserDao.getInstance();
    private static final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private static final UserMapper userMapper = UserMapper.getInstance();
    public List<UserDto> findAll() {
        return null;
    }

    public Long create(CreateUserDto createUserDto) {
        try {
            var validationResult = createUserValidator.isValid(createUserDto);
            if (!validationResult.isValid()) {
                throw new ValidationException(validationResult.getErrors());
            }
            return userDao.save(CreateUserMapper.getInstance().mapFrom(createUserDto)).getId();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<UserDto> login(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password).map(userMapper::mapFrom); 
    }

    public Long uploadPhoto(String path) {
        return null;
    }
    public static UserService getInstance() {
        return INSTANCE;
    }
}
