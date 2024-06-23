package com.javaweb.repository.IMPL;

import com.javaweb.repository.IGeneric;
import com.javaweb.repository.mapper.IRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AbstractRepository<T> implements IGeneric<T> {

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/estatebasic?allowPublicKeyRetrieval=true&useSSL=false";
        String username = "root";
        String password = "Minhnguyen12345!";
        return DriverManager.getConnection(url, username, password);
    }
    @Override
    public List<T> query(String sql, IRowMapper<T> rowMapper, Object... params) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        setParams(statement, params);
        ResultSet resultSet = statement.executeQuery();
        List<T> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(rowMapper.rowMapper(resultSet));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return result;
    }

    private void setParams(PreparedStatement statement, Object... params) throws SQLException{
        for(int i = 0; i < params.length; i++) {
            int index = i + 1;
            Object param = params[i];
            if(param instanceof Long) {
                statement.setLong(index, (Long)param);
            }
            else if(param instanceof String) {
                statement.setString(index, (String)param);
            }
        }
    }
}
