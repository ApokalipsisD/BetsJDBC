package com.bets.service.validator.impl;

import com.bets.service.dto.MatchDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class MatchValidator implements Validator<MatchDto, Integer> {


    @Override
    public void validate(MatchDto value) throws ServiceException {
        validateCoefficient(value.getFirstCoefficient());
        validateCoefficient(value.getSecondCoefficient());
        validateScore(value.getFirstTeamScore());
        validateScore(value.getSecondTeamScore());
        validateDate(value.getDate());
    }

    private void validateCoefficient(BigDecimal coefficient) throws ServiceException {
        if (Objects.isNull(coefficient)) {
            throw new ServiceException(MessageException.COEFFICIENT_IS_NULL_EXCEPTION);
        }
        if (coefficient.compareTo(BigDecimal.valueOf(1)) < 0) {
            throw new ServiceException(MessageException.INCORRECT_COEFFICIENT_EXCEPTION);
        }

    }

    private void validateScore(Integer score) throws ServiceException {
        if (Objects.isNull(score)) {
            throw new ServiceException(MessageException.SCORE_IS_NULL_EXCEPTION);
        }

        if (score < 0) {
            throw new ServiceException(MessageException.INCORRECT_SCORE_EXCEPTION);
        }
    }

    private void validateDate(Timestamp date) throws ServiceException {
        if (Objects.isNull(date)) {
            throw new ServiceException(MessageException.DATE_IS_NULL_EXCEPTION);
        }

        if (date.before(new Timestamp(System.currentTimeMillis()))) {
            throw new ServiceException(MessageException.INCORRECT_DATE_EXCEPTION);
        }
    }
}
