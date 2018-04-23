package test;

/**
 * Created by WaterMelon on 2018/4/8.
 *
 * 常用函数说明
 * 1） sleep(long mills)：在指定的毫秒数内让当前正在执行的线程休眠（暂停执行）
 * 2） join()：指等待t线程终止
 * 使用方式：join()是thread类的一个方法，启动线程后直接调用，即join（）的作用是：等待该线程终止 ， 这里需要理解的就是该线程是指的主线程等待子线程的终止。也就是在子线程调用了join()方法后面的代码，只有等到子线程结束了才能执行。
 * Thread t = new AThread(); t.start(); t.join;
 *
 * join的使用情况
 * 在很多情况下，主线程生成并启动了子线程，如果子线程里要进行大量的耗时的运算，主线程往往将于子线程之前结束，但是如果主线程处理完其他的事务后，需要用到子线程的处理结果，也就是主线程需要等待子线程执行完成之后再结束，这个时候就要用到join()方法。
 *
 */
public class joinTest implements Runnable {

    private String name;

    public joinTest(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始运行！");
        for (int i = 0;i<5;i++){
            System.out.println("子线程"+name+"run:"+i);
            try {
                Thread.sleep((int)Math.random()*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程结束运行！");
    }
}
