package com.quanlybanhang.orm.query.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NamedParamStatement extends NativeSQLStatement {
    private List<String> paramList = new ArrayList<>();

    public NamedParamStatement(Connection connection, String sql) throws SQLException {
        int pos;
        StringBuilder sqlBuilder = new StringBuilder(sql);
        while ((pos = sqlBuilder.indexOf("{")) != -1) {
            int end = sqlBuilder.indexOf("}", pos);
            paramList.add(sqlBuilder.substring(pos + 1, end));
            sqlBuilder.replace(pos, end + 1, "?");
        }
        System.out.println(sqlBuilder.toString());
        preparedStatement = connection.prepareStatement(sqlBuilder.toString());
    }

    public void setNamedParam(String name, Object value) throws SQLException {
        int paramIndex = paramList.indexOf(name);
        if (paramIndex == -1) {
            throw new SQLException("Param " + name + " does not exists");
        }
     
        setParamAt(paramIndex + 1, value);
    }
/*
    public void setNamedParamMap(Map<String, NamedParam> namedParamMap) throws SQLException {
        for (Map.Entry<String, NamedParam> entry : namedParamMap.entrySet()) {
            NamedParam namedParam = entry.getValue();
            this.setNamedParam(namedParam.getPropertyName(), namedParam.getValue());
        }
    }
*/
}
