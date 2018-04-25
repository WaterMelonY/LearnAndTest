package test;

import org.junit.Test;
import service.WebServiceImpl;

import java.io.File;

/**
 * Created by WaterMelon on 2018/4/4.
 */
public class test {
    public static void main(String[] args) {
//        test();
//        testThread();
//        testRunnable();
//        nojoinTest();
//        joinTest();
//        yield();
//        waitNotifyTest();
//        waitNotifyTest2();
        testXMlToBean();
    }

    public static void testXMlToBean(){
//        Element note = XmlUtils.createRootElement("note");
//        XmlUtils.appendElement(note, "to", "George1");
//        XmlUtils.appendElement(note, "from", "John2");
//        XmlUtils.appendElement(note, "heading", "Reminder1");
//        XmlUtils.appendElement(note, "body", "Don't forget the meeting!");
//
//        XmlUtils.saveToXml(note, new File("C:\\Users\\WaterMelon\\Desktop\\temp.xml"));

        //使用xml生成工具生成java代码(控制台输出)
        File file = new File("C:\\Users\\WaterMelon\\Desktop\\temp.xml");
        XmlUtils.createJavaCode(file);
    }

    public static void test(){
        String json = "{MessageType:'CAJob'," +
                "MessageID:'27a208a0-3aab-48f0-873c-d0726453ef70'," +
                "OriginatorAddress:'MJM'," +
                "RecipientAddress:'IPA'," +
                "JobID:'/CA/MUX'," +
                "SatelliteID:'ZY3-02'," +
                "SensorID:'MUX'," +
                "OrbitID:'4656'," +
                "ScenePath:'test'," +
                "SceneRow:'test'," +
                "ProductOutPath:'/DiskArray/iecas/root/Output/BM/ZY3-02/BM_4656_Product/ZY3/MUX/'," +
                "ProcessOutPath:'/DiskArray/iecas/root/Tepro/ZY3-02/ZY3-02_4656_20170401_AUT_000319/BM/CA/MUX/'," +
                "HelperInPath:'/DIS/Config/'," +
                "OutJobStatusFileName:'/DiskArray/iecas/root/Tepro/ZY3-02/ZY3-02_4656_20170401_AUT_000319/BM/CA/MUX/JobState.xml'," +
                "OutJobCompleteFileName:'/DiskArray/iecas/root/Tepro/ZY3-02/ZY3-02_4656_20170401_AUT_000319/BM/CA/MUX/JobComplete.xml'," +
                "RawFileName:'/DiskArray/iecas/004656/MUX/'," +
                "CAXMLFileName:'/DiskArray/iecas/root/Tepro/ZY3-02/ZY3-02_4656_20170401_AUT_000319/BM/CA/MUX/KAS-ZY302-MUX-20170401-004656-0000002167_SASMAC.XML'," +
                "DATFileName:'test'," +
                "BrowseFileTempLocation:'test'," +
                "ThumImage:'test'," +
                "RawDataType:'1'," +
                "ResultPath:'/DiskArray/iecas/root/dpps/meta/2018/0425/projectTest_20180321_0000000001.result.xml'}";
//        System.out.println(json);
//        JSONObject.fromObject(json);
        new WebServiceImpl().testCA(json);
    }

    /**
     * start()方法的调用后并不是立即执行多线程代码，而是使得该线程变成可运行状态（Runnable）什么时候运行是由操作系统决定的。
     *
     */
    public static void testThread(){
        Thread a = new ThreadTest("A");
        Thread b = new ThreadTest("B");
        a.start();
        b.start();
    }

    /**
     * run()方法是多线程的一个约定。所有的多线程代码都在run方法里面。
     */
    public static void testRunnable() {
        Thread a = new Thread(new RunnableTest("C"));
        Thread b = new Thread(new RunnableTest("D"));
        a.start();
        b.start();
    }

    public static void nojoinTest(){
        System.out.println(Thread.currentThread().getName()+"main thread is run");
        Thread a = new Thread(new joinTest("A"));
        Thread B = new Thread(new joinTest("B"));
        a.start();
        B.start();
        System.out.println(Thread.currentThread().getName()+"main thread is end");
    }

    public static void joinTest(){
        System.out.println(Thread.currentThread().getName()+"main thread is run");
        Thread a = new Thread(new joinTest("A"));
        Thread B = new Thread(new joinTest("B"));
        a.start();
        B.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            B.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"main thread is end");
    }

    @org.junit.Test
    public void yield(){
        yieldTest a = new yieldTest("A");
        yieldTest b = new yieldTest("B");
        a.start();
        b.start();
    }

    public static void waitNotifyTest() {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        WaitTest pa = new WaitTest("A",c,a);
        WaitTest pb = new WaitTest("B",a,b);
        WaitTest pc = new WaitTest("C",b,c);

        new Thread(pa).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(pb).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(pc).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitNotifyTest2(){
        final byte a[] = {0};//以该对象为共享资源
        Thread thread1 = new Thread(new WaitTest2(1,a),"1");
        Thread thread2 = new Thread(new WaitTest2(2,a),"2");
        thread1.start();
        thread2.start();
    }

    @Test
    public void unSafeSingleton(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                UnSafeSingleton.getInstance();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                UnSafeSingleton.getInstance();
            }
        }).start();
    }
}
