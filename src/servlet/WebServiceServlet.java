package servlet;

import service.WebServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.ws.Endpoint;

/**
 * Created by WaterMelon on 2018/4/4.
 */
public class WebServiceServlet extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
//        Endpoint.publish("http://0.0.0.0:8888/UIParameter/ws?wsdl", new WebServiceImpl());
        Endpoint.publish("http://127.0.0.1:8888/CAtest/ws?wsdl", new WebServiceImpl());
        System.out.println("webservice发布成功");
    }
}
