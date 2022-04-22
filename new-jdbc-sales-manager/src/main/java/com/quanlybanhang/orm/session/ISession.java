package com.quanlybanhang.orm.session;

import java.sql.SQLException;

import com.quanlybanhang.orm.query.sqlquery.ISQLQuery;

public interface ISession {
	ISQLQuery createSQLQuery(String sql) throws SQLException; 
}
