package test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Created by WaterMelon on 2018/4/26.
 */
public class XmlToBean {
    private final static String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

    private static XStream xStream = null;

    static{
        xStream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{First.class, inputfilelist.class,outputfilelist.class});
        /*
         * 使用xStream.alias(String name, Class Type)为任何一个自定义类创建到类到元素的别名
         * 如果不使用别名，则生成的标签名为类全名
         */
        xStream.alias("task", First.class);
        xStream.alias("inputfilelist", inputfilelist.class);
        xStream.alias("outputfilelist", outputfilelist.class);
//        xStream.alias("address", Address.class);
        //将某一个类的属性，作为xml头信息的属性，而不是子节点
        xStream.useAttributeFor(First.class, "name");
        xStream.useAttributeFor(First.class, "id");
        xStream.useAttributeFor(First.class, "orderid");

        xStream.useAttributeFor(inputfilelist.class, "num");
        xStream.useAttributeFor(outputfilelist.class, "num");
        xStream.processAnnotations(First.class);
        xStream.processAnnotations(inputfilelist.class);
        xStream.processAnnotations(outputfilelist.class);

        xStream.addImplicitCollection(First.class,"inputfilelist");
        //对属性取别名
//        xStream.aliasField("省", Address.class,"province");
    }

    private XmlToBean(){}

    /**
     * 序列化XML
     *
     * @param obj
     * @return
     */
    public static<T> String toXml(Object obj){
        xStream = getXStream();
        xStream.processAnnotations(obj.getClass());
        return new StringBuffer(XML_DECLARATION).append(xStream.toXML(obj)).toString();
    }

    /**
     * 反序列化XML
     *
     * @param xmlStr
     * @param clazz
     * @return
     */
    public static <T> T fromXML(String xmlStr, Class<T> clazz) {
        xStream = getXStream();
        xStream.processAnnotations(clazz);
        Object obj = xStream.fromXML(xmlStr);
        try {
            return clazz.cast(obj);
        } catch (ClassCastException e) {
            return null;
        }
    }

    /**
     * 获取Xstream实例
     *
     * @return
     */
    public static XStream getXStream() {
//        return new XStream();
        return xStream;
    }

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n" +
                "<task name=\"\" id=\"\" desc=\"\" orderid=\"\">\n" +
                "\t<inputfilelist num=\"1\">\n" +
                "\t<l0Data>/mnt/lfs/l0datatemp/JB13A-1/2015/0729/368_1507290001/368_1507290001.100001</l0Data>\n" +
                "\t</inputfilelist>\n" +
                "\t<outputfilelist num=\"2\">\n" +
                "\t\t<reportFile>/DiskArray/data/l0datatemp/JB13A-1/2015/0729/368_1406050001/taskid.report.xml</reportFile>\n" +
                "\t\t<resultFile>/DiskArray/ars/meta/2015/0729/taskid.result.xml</resultFile>\n" +
                "\t</outputfilelist>\n" +
                "\t<params>\n" +
                "\t\t<satelliteId>JB13A-1</satelliteId>\n" +
                "\t\t<stationID></stationID>\n" +
                "\t\t<trplanid></trplanid>\n" +
                "\t\t<pbtaskid></pbtaskid>\n" +
                "\t\t<sensor>SAR</sensor>\n" +
                "\t\t<segmentIDPrefix>JB13A-1_SAR_000000368</segmentIDPrefix>\n" +
                "\t\t<segmentDirRoot>/DiskArray/data/catalog/JB13A-1/2015/0729/</segmentDirRoot>\n" +
                "\t\t<server_address></server_address>\n" +
                "\t\t<server_port></server_port>\n" +
                "\t</params>\n" +
                "</task>";


        XStream test = new XStream(new DomDriver());
        test.alias("task",First.class);
        test.alias("inputfilelist",inputfilelist.class);
        test.alias("outputfilelist",outputfilelist.class);
        test.alias("params",params.class);
        test.addImplicitArray(First.class,"inputfilelist");
        test.addImplicitArray(First.class,"outputfilelist");
        test.addImplicitArray(First.class,"params");

//        First a = XmlToBean.fromXML(xml,First.class);

//        XStream test1 = new XStream(new DomDriver());
//        test1.alias("task",Task.class);
//        test1.alias("inputfilelist",Task.Inputfilelist.class);
//        test1.alias("outputfilelist",Task.Outputfilelist.class);
//        test1.alias("params",Task.Params.class);
//        test1.addImplicitArray(Task.class,"inputfilelist");
//        test1.addImplicitArray(Task.class,"outputfilelist");
//        test1.addImplicitArray(Task.class,"params");
//        test1.aliasField("server_address",Task.Params.class,"serverAddress");
//        test1.aliasField("server_port",Task.Params.class,"serverPort");
//        Task aaa = (Task) test1.fromXML(xml);

//        First a = (First) test.fromXML(xml);

//        String b = XmlToBean.toXml(a);
//        Task aaa = (Task) test.fromXML(xml);
//        String d = convertToXml(aaa);
//        String c = XmlToBean.toXml(aaa);
//        System.out.println(c);
//        System.out.println(b);
//        System.out.println(d);
    }


}