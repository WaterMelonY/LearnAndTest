package test;

/**
 * Created by WaterMelon on 2018/4/9.
 */
public class DataTransferByCreateMethodTest implements Runnable{
    private String name;

    public DataTransferByCreateMethodTest(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("hello"+name);
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new DataTransferByCreateMethodTest("water"));
        thread.start();
    }
}

/**
 * 通过构造方法传递数据
 * 由于这种方法是在创建线程对象的同时传递数据的，因此，在线程运行之前这些数据就已经到位了，这样就不会造成数据在线程运行后才传入的现象。
 * 如果要传递更复杂的数据，可以使用集合，类等数据结构。使用构造方法传递数据虽然比较安全，但如果要传递的数据比较多，就会造成很多不便。
 * 由于java没有默认参数，要想实现类似默认参数的效果，就得使用重载，这样不但使构造方法本身过于复杂，又会使构造方法在数量上大增。
 * 因此要避免，就得通过类方法，或类变量来传递数据。
 */
