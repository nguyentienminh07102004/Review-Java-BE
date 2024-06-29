package com.javaweb.repository;

import com.javaweb.repository.mapper.IRowMapper;

import java.sql.SQLException;
import java.util.List;

public interface IGeneric<T> {
    List<T> query(String sql, IRowMapper<T> rowMapper, Object... params);
}
