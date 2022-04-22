package com.quanlybanhang.orm.query.statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NativeSQLStatement {
    protected PreparedStatement preparedStatement = null;

    public NativeSQLStatement() {
    }

    public ResultSet executeQuery() throws SQLException {
        System.out.println(preparedStatement);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        return resultSet;
    }

    public Integer executeUpdate() throws SQLException {
        System.out.println(preparedStatement);
        return this.preparedStatement.executeUpdate();
    }

    public Long executeInsert() throws SQLException {
        System.out.println(preparedStatement);
        this.preparedStatement.executeUpdate();
        ResultSet resultSet = this.preparedStatement.getGeneratedKeys();

        if (resultSet != null && resultSet.next()) {
            return resultSet.getLong(1);
        }

        return null;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setParamAt(int index, Object parameter) throws SQLException { 	
    	if (parameter instanceof Long) {
    		this.preparedStatement.setLong(index, (Long) parameter);
		} else if (parameter instanceof String) {
			this.preparedStatement.setString(index, (String) parameter);
		} else if (parameter instanceof Integer) {
			this.preparedStatement.setInt(index, (Integer) parameter);
		} else if (parameter instanceof Double) {
			this.preparedStatement.setDouble(index, index);
		}else if (parameter instanceof Timestamp) {
			this.preparedStatement.setTimestamp(index, (Timestamp) parameter);
		} else if (parameter instanceof Object) {
			this.preparedStatement.setObject(index, (Timestamp) parameter);
		} 
    }
}
