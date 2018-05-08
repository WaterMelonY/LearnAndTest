package test;

/**
 * Created by job on 2017/6/2.
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoolConnection {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc\\:mysql\\://172.24.10.5\\:3306/TH?useUnicode\\=true&autoReconnect\\=true&characterEncoding\\=UTF-8&zeroDateTimeBehavior\\=convertToNull";
    private static final String DB_NAME = "root";
    private static final String DB_PWD = "root00";

    private static final String SQL = "SELECT * FROM ";// 数据库操作


    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_NAME, DB_PWD);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeDB(ResultSet rs, Statement stt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("结果集关闭错误！");
            }

        }

        if (stt != null) {
            try {
                stt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("sql池关闭错误！");
            }

        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("链接关闭错误！");
            }

        }


    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
//                LOGGER.error("close connection failure", e);
            }
        }
    }

    /**
     * 获取表中所有字段名称
     *
     * @param tableName 表名
     * @return
     */
    public static List<String> getColumnNames(String tableName, String ClassName) {
        List<String> columnNames = new ArrayList<>();
        //与数据库的连接
        Connection conn = getConnection();
        PreparedStatement pStemt = null;
        String tableSql = SQL + tableName;
        try {
            pStemt = conn.prepareStatement(tableSql);
            //结果集元数据
            ResultSetMetaData rsmd = pStemt.getMetaData();
            //表列数
            int size = rsmd.getColumnCount();
            for (int i = 0; i < size; i++) {
                columnNames.add(rsmd.getColumnName(i + 1));
                //获取每个字段的名称
                Object javaBean = Class.forName(ClassName).newInstance();

            }
        } catch (SQLException e) {
//            LOGGER.error("getColumnNames failure", e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (pStemt != null) {
                try {
                    pStemt.close();
                    closeConnection(conn);
                } catch (SQLException e) {
//                    LOGGER.error("getColumnNames close pstem and connection failure", e);
                }
            }
        }
        return columnNames;
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        File file = new File("C:\\Users\\WaterMelon\\Desktop\\xmlFile\\finsh.xml");
        ReqInterfaceFile finshInterfaceFile = new xmlBeanTest<ReqInterfaceFile>(new ReqInterfaceFile()).getObjByOrderName(file);
        List<Object> a = finshInterfaceFile.getFileHeadOrFileBody();

//        FinshInterfaceFile.FileHead fileHead = (FinshInterfaceFile.FileHead)a.get(0);
//        FinshInterfaceFile.FileBody fileBody = (FinshInterfaceFile.FileBody)a.get(1);
//        System.out.println(fileBody.getTrPlanID());
//        System.out.println(fileHead.getCreationTime());
        Field[] fields = finshInterfaceFile.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
            String fieldType = field.getAnnotatedType().getType().getTypeName();

            System.out.println(fieldType);
            if (fieldType.indexOf("java.util.List") != -1) {
                System.out.println("islist");

                testa(field);
            }
        }

    }

    public static void testa(Field field) {
        //获取字段上的注解
        boolean fieldHasAnno = field.isAnnotationPresent(XmlElements.class);
        if (fieldHasAnno) {
            XmlElements xmlElements = field.getAnnotation(XmlElements.class);
            //输出注解属性
            XmlElement[] xmlElements1 = xmlElements.value();
            for (XmlElement xmlElement : xmlElements1) {
                String name = xmlElement.name();
                Class type = xmlElement.type();
                System.out.println(field.getName() + " name = " + name + ", type = " + type);
            }
        }
    }

}
