package com.athena.utils;

/**
 * @Project:api_athena
 * @Date: 2022/3/20 10:09
 * @Author:Athena
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static Connection getConnection(){
    //定义数据库连接
    //jdbc:oracle:thin:@localhost:1521:DataBaseName
    String url = Constans.JDBC_URL;
    String user = Constans.JDBC_USER;
    String password = Constans.JDBC_PASSWORD;
    //定义数据库连接对象
    Connection conn = null;
        try {
            //你导入的数据库驱动包mysql。
            conn = DriverManager.getConnection(url, user,password);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
}
    public static void close(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
