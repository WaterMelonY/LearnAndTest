package ftp;

import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WaterMelon on 2018/4/25.
 */
public class FtpListener implements Runnable{
//    private static Logger logger = LoggerFactory.getLogger(LocalFTPListener.class);
//    private FTPEntity ftpEntity;
    private String localIp;
    private String redisHost;
    private int redisPort;

    @Override
    public void run() {

        SimpleDateFormat dateFormate = new SimpleDateFormat("yyyyMMddHHmm");

        boolean reConnect = true;

        long startTimeMillis = 0;// 开始监听时间
        long sysTimeMillis = 0;// 当前系统时间

        FTPUtils ftpUtils = FTPUtils.getInstance();
        if(ftpUtils.open()){
            System.out.println("链接成功");
            startTimeMillis = System.currentTimeMillis();
            try {
                List<FTPFile> firstFile = new ArrayList(Arrays.asList(ftpUtils.getFirstDirectory()));
                while (true){
                    //获取监听目录下及其子目录下的所有文件集合
                    //获取当天的文件包最后上传时间的字符串
                    List<FTPFile> filesAll = new ArrayList(Arrays.asList(ftpUtils.getFirstDirectory()));
                    if(filesAll.size()>firstFile.size()){
                         for (FTPFile file:filesAll) {
                            for(FTPFile file1:firstFile){
                                if((file1.getName()).equals(file.getName())){
                                    System.out.println("文件已经存在");
                                    break;
                                }else{
                                    System.out.println("这是新创建的文件");
//                                    firstFile.add(file);
                                    System.out.println(file.getName()+" >>>>>> "+file.getSize() + " <<<<<<<>>>>>> " );
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[8192];
        int len;
        try {
            digest =MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
