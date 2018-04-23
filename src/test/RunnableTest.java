package test;

/**
 * Created by WaterMelon on 2018/4/8.
 */
public class RunnableTest implements Runnable{
    private String name;

    public RunnableTest(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i=0;i<5;i++){
            System.out.println(name+"运行："+i);
            try {
                Thread.sleep((int)Math.random()*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * Thread和Runnable的区别
 * 如果一个类继承Thread，则不适合资源共享。但是如果实现了Runnable接口，则很容易实现资源共享；
 *
 * 实现Runnable接口比继承Thread类所具有的优势
 * 适合多个相同程序代码的线程去处理同一个资源
 * 可以避免java中的单继承的限制
 * 增加程序的健壮性，代码可以被多个线程共享，代码和数据独立
 * 线程池只能放入实现Runnable或callable类线程，不能直接放入继承Thread的类
 */

/**
 * Thread不适合做资源共享的原因（个人理解）： 一个线程只能启动一次，通过Thread实现线程时，线程和线程所要执行的任务是捆绑在一起的。
 * 也就使得一个任务只能启动一个线程，不同的线程执行的任务是不同的，所以没有必要，也不能让两个线程共享彼此任务中的资源。
 *
 * 一个任务可以启动多个线程，通过Runnable方式实现的线程，实际上是开辟一个线程，将任务传递进去，由线程来执行。
 * 可以实例化多个Thread对象，将同一任务传递进去，也就是一个任务可以启动多个线程来执行。这些线程执行的是同一任务，所以他们的资源是共享的。
 *
 */

/**
 * 线程调度
 * 1.调整线程的优先级：java线程有优先级，优先级高的线程会获得较多的运行机会
 * java线程的优先级用整数表示，取值范围是1~10，Thread类有以下三个静态常量：
 * static int MAX_PRIORITY  线程可以具有的最高优先级，10
 * static int MIN_PRIORITY  线程可以具有的最低优先级，1
 * static int NORM_PRIORITY 分配给线程的默认优先级，5
 *
 * Thread类的setPriority() and getPriority() is used to set and get thread's priority;
 *
 *every thread has default priority. main thread priority is Thread.NORM_PRIORITY.
 * thread priority will be extend by parent Class.E：A create B ,so A and B has the same priority.
 *
 * 2.线程睡眠 Thread.sleep(long millis) will change thread's status be blocked. the params millis is set sleep time.
 *      when sleep end ,will change runnable . sleep()平台移植性好。
 *
 * 3.线程等待：object类中的wait()方法，导致当前的线程等待，直到其他线程调用此对象的notify()方法或notifyAll()唤醒方法。这两个唤醒方法也是Object类中的方法，行为等价于调用wait(0)一样
 *
 * 4.线程让步：Thread.yield()方法，暂停当前正在执行的线程对象，把执行机会让给相同或者更高优先级的线程。
 *
 * 5.线程加入：join()方法，等待其他线程终止。在当前线程中调用另一个线程的join()方法，则当前线程转入阻塞状态，直到另一个线程运行结束，当前线程再由阻塞转为就绪状态。
 *
 * 6.线程唤醒：Object类中的notify()方法，唤醒在此对象监视器上等待的单个线程。如果所有线程都在此对象上等待，则会选择唤醒其中一个线程。选择是随机的，并对实现做出决定时发生。
 *  线程通过调用其中一个wait方法，在对象的监视器上等待。直到当前的线程放弃此对象上的锁定，才能继续执行被唤醒的线程。被唤醒的线程将以常规方式与在该对象上主动同步的其他所有线程进行竞争；
 * 例如：唤醒的线程在作为锁定此对象的下一个线程方面没有可靠的特权或劣势。类似的方法还有一个notifyAll()，唤醒在此对象监视器上等待的所有线程。
 *
 */