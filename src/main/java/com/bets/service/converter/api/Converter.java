package com.bets.service.converter.api;

import com.bets.dao.model.Entity;
import com.bets.service.dto.AbstractDto;

public interface Converter<T extends Entity<K>, V extends AbstractDto<K>, K> {

    T convert(V entityDto);

    V convert(T entity);
}
