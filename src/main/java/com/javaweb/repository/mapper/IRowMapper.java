package com.javaweb.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IRowMapper<T> {
    T rowMapper(ResultSet resultSet) throws SQLException;
}
