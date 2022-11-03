package com.bets.service.converter.impl;

import com.bets.dao.model.User;
import com.bets.service.converter.api.Converter;
import com.bets.service.dto.UserDto;

public class UserConverter implements Converter<User, UserDto, Integer> {
    @Override
    public User convert(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getAge(),
                userDto.getEmail(),
                userDto.getBalance(),
                userDto.getRole().getId()
        );
    }

    @Override
    public UserDto convert(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail(),
                user.getBalance(),
                user.getRole().getId()
        );
    }
}
