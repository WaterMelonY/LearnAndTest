package ftp;

/**
 * Created by WaterMelon on 2018/4/25.
 */
public class ftpTest {
    public static void main(String[] args) {
//        FTPUtils ftpUtils = FTPUtils.getInstance();
//        //开启连接 并检测是否成功
//        boolean isSuccess =ftpUtils.open();
//        System.out.println(isSuccess);
//        ftpUtils.mkdir("end");
//        ftpUtils.mkdir("over");
//        ftpUtils.upload("test.xml","start","D:\\CH\\testXml\\Process\\THSegmentProcess20180424044128.xml");
        new Thread(new FtpListener()).start();
    }

}
