package com.quanlybanhang.orm.session;

import java.sql.Connection;
import java.sql.SQLException;

import com.quanlybanhang.orm.query.sqlquery.ISQLQuery;
import com.quanlybanhang.orm.query.sqlquery.SQLQuery;

public class Session implements ISession{
	private Connection connection;
    
    public Session(Connection connection) {
        this.connection = connection;
    }

	@Override
	public ISQLQuery createSQLQuery(String sql) throws SQLException {
		return new SQLQuery(connection, sql);
	}
}
