package ling.sport.originalSources;

import java.sql.*;

/**
 * 数据库信息类
 */
public class DatabaseInformation {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://106.53.85.245:3306/bracelet?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static String username = "root";
    private static String password = "lingeros";
    public DatabaseInformation() {
        try {
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Connection getconn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            rs = null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if (st != null) {
                st.close();
            }
            st = null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
            conn = null;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

