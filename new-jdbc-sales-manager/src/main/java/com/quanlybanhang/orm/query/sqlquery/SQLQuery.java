package com.quanlybanhang.orm.query.sqlquery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.quanlybanhang.mapper.RowMapper;
import com.quanlybanhang.orm.query.statement.NamedParamStatement;

public class SQLQuery implements ISQLQuery{
    private NamedParamStatement statement;
    private Connection connection;
    

    public SQLQuery(Connection connection, String sql) {
        try {
        	setConnection(connection);
            this.statement = new NamedParamStatement(getConnection(), sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public <T> List<T> query(RowMapper<T> rowMapper) {
		List<T> results = new ArrayList<>();
		ResultSet resultSet = null;
        try {
            resultSet = this.statement.executeQuery();
            while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
            connection.commit();
            return results;
        } catch (SQLException e) {
        	if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					return null;
				}
			}
        	return null;
        } finally {
        	try {
        		if(resultSet != null) {
            		resultSet.close();
            	}
        		if(getConnection() != null) {
        			getConnection().close();
        		}
        		if(this.statement != null) {
        			this.statement.getPreparedStatement().close();
        		}
			} catch (Exception e2) {
				return null;
			}
        }
	}

	@Override
	public int executeUpdate() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setParam(int index, Object parameter) throws SQLException {
		this.statement.setParamAt(index, parameter);
	}

	@Override
	public void setParam(String namedParam, Object parameter) throws SQLException {
		this.statement.setNamedParam(namedParam, parameter); 
	}

	@Override
	public void setParamMap(Map<String, Object> paramMap) throws SQLException {
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            this.setParam(entry.getKey(), entry.getValue());
        }	
	}


    
}
