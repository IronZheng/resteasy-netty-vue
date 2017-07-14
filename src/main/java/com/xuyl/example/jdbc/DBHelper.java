package com.xuyl.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dell on 2017/7/14.
 */
public class DBHelper {

    public static final String url = "jdbc:mysql://tdsql-a69ho1op.sh.cdb.myqcloud.com:43/test";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "iron";
    public static final String password = "mysql123!";

    public Connection conn = null;
    public PreparedStatement pst = null;

    public DBHelper(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            pst = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.conn.close();
            this.pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
