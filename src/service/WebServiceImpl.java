package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WaterMelon on 2018/4/4.
 */
@WebService
public class WebServiceImpl implements WebServiceTest {

    @WebMethod
    @Override
    public String testCA(String xml) {
//        System.out.println("开始进行分镜");
        CAXmlTest caXmlTest = new CAXmlTest();
        caXmlTest.testCreateXML(xml);

        return null;
    }
}
