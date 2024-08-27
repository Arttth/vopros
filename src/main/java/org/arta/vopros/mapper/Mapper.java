package org.arta.vopros.mapper;

import java.text.ParseException;

public interface Mapper<T, K> {
    K mapFrom(T object) throws ParseException;
}
