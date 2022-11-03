package com.bets.service.validator.impl;

import com.bets.service.dto.UserBetOnMatchDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;

import java.math.BigDecimal;
import java.util.Objects;

public class UserBetOnMatchValidator implements Validator<UserBetOnMatchDto, Integer> {
    @Override
    public void validate(UserBetOnMatchDto value) throws ServiceException {
        validateBet(value.getBet());
        validateCoefficient(value.getCoefficient());
        validateEarnings(value.getEarnings());
    }

    private void validateBet(BigDecimal bet) throws ServiceException {
        if (Objects.isNull(bet)) {
            throw new ServiceException(MessageException.BET_IS_NULL_EXCEPTION);
        }

        if (bet.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ServiceException(MessageException.INCORRECT_BET_EXCEPTION);
        }
    }

    private void validateCoefficient(BigDecimal coefficient) throws ServiceException {
        if (Objects.isNull(coefficient)) {
            throw new ServiceException(MessageException.COEFFICIENT_IS_NULL_EXCEPTION);
        }
        if (coefficient.compareTo(BigDecimal.valueOf(1)) < 0) {
            throw new ServiceException(MessageException.INCORRECT_COEFFICIENT_EXCEPTION);
        }
    }

    private void validateEarnings(BigDecimal earnings) throws ServiceException {
        if (Objects.nonNull(earnings) && earnings.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new ServiceException(MessageException.INCORRECT_EARNINGS_EXCEPTION);
        }
    }
}
