package service;

import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import redis.clients.jedis.Jedis;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WaterMelon on 2018/4/4.
 */
public class CAXmlTest {
//    private static final String TASK ="testzh";
//    private static final String PROCESS ="dataTest";

    private static final String TASK ="CATest";
    private static final String PROCESS ="projectTest";

    public String testCreateXML(String xml){

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String time = dateFormat.format(date);

        Document doc = DocumentHelper.createDocument();
        JSONObject jsonObject = JSONObject.fromObject(xml);

        System.out.println(PROCESS+time);
        Element root = doc.addElement("process-order").addAttribute("id", PROCESS+time).addAttribute("name", PROCESS)
                .addAttribute("priority", "1");

        Element task = root.addElement("task");
        String orderid = PROCESS+"_"+time;
        String taskId = TASK+"_"+time;
        task.addAttribute("orderid",orderid);
        task.addAttribute("priority","2");
        task.addAttribute("id",taskId);
        task.addAttribute("name",TASK);

        Element root1 = task.addElement("root");
        Element head = root1.addElement("head");
        head.addElement("MessageType").setText(jsonObject.getString("MessageType"));
        head.addElement("MessageID").setText(jsonObject.getString("MessageID"));
        head.addElement("OriginatorAddress").setText(jsonObject.getString("OriginatorAddress"));
        head.addElement("RecipientAddress").setText(jsonObject.getString("RecipientAddress"));
        head.addElement("CreationTime").setText(time);

        Element content = root1.addElement("content");

        content.addElement("TaskID").setText(taskId);

        content.addElement("JobID").setText(jsonObject.getString("JobID"));
        content.addElement("SatelliteID").setText(jsonObject.getString("SatelliteID"));
        content.addElement("OrbitID").setText(jsonObject.getString("OrbitID"));
        content.addElement("ScenePath").setText(jsonObject.getString("ScenePath"));
        content.addElement("SceneRow").setText(jsonObject.getString("SceneRow"));
        content.addElement("ProductOutPath").setText(jsonObject.getString("ProductOutPath"));
        content.addElement("ProcessOutPath").setText(jsonObject.getString("ProcessOutPath"));
        content.addElement("HelperInPath").setText(jsonObject.getString("HelperInPath"));
        content.addElement("OutJobStatusFileName").setText(jsonObject.getString("OutJobStatusFileName"));
        content.addElement("OutJobCompleteFileName").setText(jsonObject.getString("OutJobCompleteFileName"));
        content.addElement("RawFileName").setText(jsonObject.getString("RawFileName"));
        content.addElement("CAXMLFileName").setText(jsonObject.getString("CAXMLFileName"));
        content.addElement("DATFileName").setText(jsonObject.getString("DATFileName"));
        content.addElement("BrowseFileTempLocation").setText(jsonObject.getString("BrowseFileTempLocation"));
        content.addElement("BrowseFileTempLocation").setText(jsonObject.getString("BrowseFileTempLocation"));
        content.addElement("ThumImage").setText(jsonObject.getString("ThumImage"));
        content.addElement("RawDataType").setText(jsonObject.getString("RawDataType"));
        String aa = "/DiskArray/iecas/root/dpps/meta/2018/0503/"+taskId+".result.xml";
        content.addElement("ResultPath").setText(aa);
        System.out.println(doc.asXML());
        outputXml(doc, "D:/CH/testXml/Process/"+ orderid + ".xml");

//        RedisUtil.submit(doc.asXML());
        //像redis中添加列表并将xml文件存放到列表中
        String redisIP = "172.24.10.161";
        int redisPort = 8715;
        int intervalTime = 6000;
        Jedis redis = new Jedis(redisIP, redisPort, intervalTime);
        redis.rpush("dpps:queue:order", doc.asXML());
        redis.close();

        //dataList
//        List<String> dataList = new ArrayList<String>();
//        dataList.add("testData1");
//        dataList.add("testData2");
//        dataList.add("testData3");
//        dataList.add("testData4");
        return "";
    }

    public static void outputXml(Document doc, String fileName) {
        XMLWriter xmlWrite = null;
        try {
            // 创建一个FileWriter对象，指定目标文件
            FileWriter fw = new FileWriter(fileName);
            // 指定xml文件的输出格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 定义编码格式
            format.setEncoding("UTF-8");
            // 写出xml文件到操作系统
            xmlWrite = new XMLWriter(fw, format);
            // 写出文档对象
            xmlWrite.write(doc);
            xmlWrite.close();
        } catch (Exception e) {
        } finally {
            if (xmlWrite != null) {
                try {
                    xmlWrite.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
