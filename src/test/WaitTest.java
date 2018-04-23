package test;

/**
 * Created by WaterMelon on 2018/4/8.
 *
 * wait()
 * Obj.wait(),与Obj.notify()必须要与synchronized(Obj)一起使用，也就是wait 与 notify 是针对已经获取了Obj锁进行操作，从语法角度来说就是Obj.wait(),Obj.notify必须在synchronized（Obj）{...}语句块内。
 * 从功能上来说 wait 就是说线程在获取对象锁后，主动释放对象锁，同时本线程休眠。直到有其他线程调用对象的notify()唤醒该线程，才能继续获取对象锁，并继续执行。
 * 相应的notify()就是对对象锁的唤醒操作。但有一点需要注意的是notify()调用后，并不是马上就释放对象锁的，而是在相应的synchronized(){}语句块执行结束，自动释放锁后，
 * JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。这样就提供了线程间同步，唤醒的操作。Thread.sleep()与Object.wait()二者都可以暂停当前线程，释放cpu控制权，
 * 主要区别在于Object.wait()在释放CPU同时，释放了对象锁的控制。
 *
 * 三线打印ABC:
 * 建立三个线程 A打印10次A B打印10次B C打印10次C 要求线程同时运行，交替打印10ABC
 */
public class WaitTest implements Runnable{
    private String name;
    private Object prev;
    private Object self;

    public WaitTest(String name,Object prev,Object self){
        this.name = name;
        this.prev = prev;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;
        while (count>0){
            synchronized (prev){
                synchronized (self){
                    System.out.print(name);
                    count--;
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
