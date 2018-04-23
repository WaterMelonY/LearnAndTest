package test;

/**
 * Created by WaterMelon on 2018/4/10.
 */
public class UnSafeSingleton {
    private static UnSafeSingleton instance = null;

    private UnSafeSingleton (){}

    public static UnSafeSingleton getInstance(){
        if(instance==null){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new UnSafeSingleton();
        }
        return instance;
    }
}
