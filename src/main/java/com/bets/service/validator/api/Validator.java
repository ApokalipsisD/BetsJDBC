package com.bets.service.validator.api;

import com.bets.service.dto.AbstractDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;

import java.util.Objects;

public interface Validator<T extends AbstractDto<K>, K> {

    void validate(T value) throws ServiceException;

    default void validateId(K value) throws ServiceException {
        if (Objects.isNull(value)) {
            throw new ServiceException(MessageException.ID_IS_NULL_EXCEPTION);
        }
        if ((Integer) value < 1) {
            throw new ServiceException(MessageException.INCORRECT_ID_EXCEPTION);
        }
    }
}