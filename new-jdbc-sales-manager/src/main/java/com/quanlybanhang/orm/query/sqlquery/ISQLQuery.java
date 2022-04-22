package com.quanlybanhang.orm.query.sqlquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.quanlybanhang.mapper.RowMapper;

public interface ISQLQuery {
	<T> List<T> query(RowMapper<T> rowMapper);
    int executeUpdate() throws SQLException;

    void setParam(int index, Object parameter) throws SQLException;

    void setParam(String namedParam, Object parameter) throws SQLException;

    void setParamMap(Map<String, Object> paramMap) throws SQLException;
}
