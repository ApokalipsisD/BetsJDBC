package com.bets.service.impl;

import com.bets.dao.api.daoApi.MatchDao;
import com.bets.dao.exception.DaoException;
import com.bets.dao.impl.MatchDaoImpl;
import com.bets.dao.model.Match;
import com.bets.service.api.MatchService;
import com.bets.service.converter.api.Converter;
import com.bets.service.converter.impl.MatchConverter;
import com.bets.service.dto.MatchDto;
import com.bets.service.exception.MessageException;
import com.bets.service.exception.ServiceException;
import com.bets.service.validator.api.Validator;
import com.bets.service.validator.impl.MatchValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatchServiceImpl implements MatchService<MatchDto, Integer> {
    private static final Logger logger = LogManager.getLogger(MatchServiceImpl.class);
    private final MatchDao<Match, Integer> matchDaoImpl = new MatchDaoImpl();
    private final Validator<MatchDto, Integer> validator = new MatchValidator();
    private final Converter<Match, MatchDto, Integer> converter = new MatchConverter();

    @Override
    public MatchDto save(MatchDto matchDto) throws ServiceException {
        validator.validate(matchDto);
        try {
            return converter.convert(matchDaoImpl.save(converter.convert(matchDto)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean update(MatchDto matchDto) throws ServiceException {
        validator.validate(matchDto);
        try {
            return matchDaoImpl.update(converter.convert(matchDto));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws ServiceException {
        validator.validateId(id);
        try {
            return matchDaoImpl.delete(id);
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public MatchDto getById(Integer id) throws ServiceException {
        validator.validateId(id);
        Match result;
        try {
            result = matchDaoImpl.findById(id);
            if (Objects.isNull(result)) {
                throw new DaoException(MessageException.MATCH_NOT_FOUND_EXCEPTION);
            }
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return converter.convert(result);
    }

    @Override
    public List<MatchDto> getAll() throws ServiceException {
        List<Match> matchList;
        List<MatchDto> matchDtoList = new ArrayList<>();
        try {
            matchList = matchDaoImpl.findAll();
            matchList.forEach(user -> matchDtoList.add(converter.convert(user)));
        } catch (DaoException e) {
            logger.error(e.getMessage() + e);
            throw new ServiceException(e.getMessage());
        }
        return matchDtoList;
    }
}
