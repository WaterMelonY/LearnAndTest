package test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by WaterMelon on 2018/4/27.
 */
public class xmlBeanTest<T> {
    private T var;

    public xmlBeanTest(T var) {
        this.var = var;
    }

    public Object getObjByOrderName() {
//        String xmlStr =getXMLByOrderName(orderName);
//        ByteArrayInputStream stream = new ByteArrayInputStream(xmlStr.getBytes());
        JAXBContext jaxbContext = null;
        Object a = null;
        try {
            jaxbContext = JAXBContext.newInstance(var.getClass());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            a = jaxbUnmarshaller.unmarshal(new File("C:\\Users\\WaterMelon\\Desktop\\temp.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return a;
    }

    /**
     * 根据插件名称获取订单模板
     *
     * @param orderName
     * @return
     */
    public static String getXMLByOrderName(String orderName) {
        String xmlStr = null;

        String sql = "select TemplateXml from PluginInfo where PluginName = '" + orderName + "'";

        Connection conn = PoolConnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                xmlStr = rs.getString("TemplateXml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return xmlStr;
    }

    /**
     * JavaBean转换成xml 默认utf-8
     * @param obj
     * @return
     *    
     */
    public static String convertToXml(Object obj) {
        return convertToXml(obj, "UTF-8");
    }

    /**
     * JavaBean转换成xml
     * @param obj
     * @param encoding
     * @return
     *    
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        ProcessOrder a = (ProcessOrder) new xmlBeanTest<ProcessOrder>(new ProcessOrder()).getObjByOrderName();
        System.out.println(a.toString());
        System.out.println(convertToXml(a));
    }
}
