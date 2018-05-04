package test;

/**
 * Created by job on 2017/6/2.
 */

import java.sql.*;

public class PoolConnection {

    private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    private  static final String  DB_URL="jdbc\\:mysql\\://172.24.10.5\\:3306/TH?useUnicode\\=true&autoReconnect\\=true&characterEncoding\\=UTF-8&zeroDateTimeBehavior\\=convertToNull";
    private  static final String  DB_NAME="root";
    private  static final String  DB_PWD="root00";
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName(JDBC_DRIVER);
            conn=DriverManager.getConnection(DB_URL,DB_NAME,DB_PWD);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeDB(ResultSet rs,Statement stt,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("结果集关闭错误！");
            }

        }

        if(stt!=null){
            try {
                stt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("sql池关闭错误！");
            }

        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("链接关闭错误！");
            }

        }


    }

}
