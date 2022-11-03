package com.bets.service.validator.impl;

import com.bets.service.dto.UserDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;

import java.math.BigDecimal;
import java.util.Objects;

public class UserValidator implements Validator<UserDto, Integer> {
    private static final Integer MIN_LOGIN_LENGTH = 3;
    private static final Integer MAX_LOGIN_LENGTH = 20;
    private static final String LOGIN_PATTERN = "^[\\w.-]{3,20}[0-9a-zA-Z]$";
    private static final Integer MIN_PASSWORD_LENGTH = 8;
    private static final Integer MAX_PASSWORD_LENGTH = 20;
    private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
    private static final Integer MIN_NAME_LENGTH = 2;
    private static final Integer MAX_NAME_LENGTH = 20;
    private static final Integer MAX_SURNAME_LENGTH = 20;
    private static final String NAME_PATTERN = "^([А-Я][а-яё]{2,20}|[A-Z][a-z]{2,20})$";
    private static final String EMAIL_PATTERN = "^([a-zA-Z0-9_-]+\\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";


    @Override
    public void validate(UserDto value) throws ServiceException {
        validateLogin(value.getLogin());
        validatePassword(value.getPassword());
        validateName(value.getName());
        validateSurname(value.getSurname());
        validateAge(value.getAge());
        validateEmail(value.getEmail());
        validateBalance(value.getBalance());
    }

    private void validateLogin(String login) throws ServiceException {
        if (Objects.isNull(login)) {
            throw new ServiceException(MessageException.LOGIN_IS_NULL_EXCEPTION);
        }

        if (!login.matches(LOGIN_PATTERN)) {
            throw new ServiceException(MessageException.INCORRECT_LOGIN_EXCEPTION);
        }

        if (login.length() < MIN_LOGIN_LENGTH
                || login.length() > MAX_LOGIN_LENGTH) {
            throw new ServiceException(MessageException.INCORRECT_LOGIN_LENGTH_EXCEPTION);
        }

    }

    private void validatePassword(String password) throws ServiceException {
        if (Objects.isNull(password)) {
            throw new ServiceException(MessageException.PASSWORD_IS_NULL_EXCEPTION);
        }

        if (password.length() < MIN_PASSWORD_LENGTH
                || password.length() > MAX_PASSWORD_LENGTH) {
            throw new ServiceException(MessageException.INCORRECT_PASSWORD_LENGTH_EXCEPTION);
        }

        if (!password.matches(PASSWORD_PATTERN)) {
            throw new ServiceException(MessageException.INCORRECT_PASSWORD_EXCEPTION);
        }

    }

    private void validateName(String name) throws ServiceException {
        if (!name.matches(NAME_PATTERN)) {
            throw new ServiceException(MessageException.INCORRECT_FIRST_NAME_EXCEPTION);
        }
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new ServiceException(MessageException.INCORRECT_FIRST_NAME_LENGTH_EXCEPTION);
        }
    }

    private void validateSurname(String surname) throws ServiceException {
        if (!surname.matches(NAME_PATTERN)) {
            throw new ServiceException(MessageException.INCORRECT_LAST_NAME_EXCEPTION);
        }
        if (surname.length() < MIN_NAME_LENGTH || surname.length() > MAX_SURNAME_LENGTH) {
            throw new ServiceException(MessageException.INCORRECT_LAST_NAME_LENGTH_EXCEPTION);
        }
    }

    private void validateAge(Integer age) throws ServiceException {
        if (age <= 0 || age > 150) {
            throw new ServiceException(MessageException.INCORRECT_AGE_EXCEPTION);
        }
    }

    private void validateEmail(String email) throws ServiceException {
        if (!email.matches(EMAIL_PATTERN)) {
            throw new ServiceException(MessageException.INCORRECT_EMAIL_EXCEPTION);
        }
    }

    private void validateBalance(BigDecimal balance) throws ServiceException {
        if (Objects.isNull(balance)) {
            throw new ServiceException(MessageException.BALANCE_IS_NULL_EXCEPTION);
        }
        if (balance.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ServiceException(MessageException.INCORRECT_BALANCE_EXCEPTION);
        }
    }
}
