package test;

/**
 * Created by WaterMelon on 2018/4/9.
 */
public class SynchronizedTest {
}

/**
 * synchronized 是java关键字，用来修饰一个方法或一个代码块时，能够保证在同一时刻最多只有一个线程执行该段代码。
 *
 * java中的synchronized 通过内置锁，来实现对变量的同步操作，进而实现了对变量操作的原子性和其他线程对变量的可见性，从而确保了并发情况下的线程安全
 *
 * 原子性：原子操作是线程安全的，这其实也是我们经常使用synchronized来实现线程安全的原因
 *
 * 可见性：可见性的意思是变量的修改可以被其他线程观察到。
 *
 * volatile只能保证可见性，不能保证原子性
 *
 *
 * synchronized 是如何保证在同一时刻最多只有一个线程执行该段代码的
 * 内置锁
 * 当我们使用synchronized修饰非静态方法时，用的是调用该方法的实例的内置锁，也就是this
 * 当我们使用synchronized修饰静态方法时，用的时调用该方法的所在的类的对象的内置锁
 * 更多的时候，我们使用的时synchronized（this），也就是把对象实例作为锁。
 *
 * 同一时间进入同一锁的线程只有一个，如果我们希望有多个线程可以同时进入多个加锁的方法，那只靠一个this锁肯定是不够的。
 *
 *
 * 重入（内置锁的可重入性）
 * 锁的持有者是“线程”，而不是“调用”
 */

/**
 *
 */