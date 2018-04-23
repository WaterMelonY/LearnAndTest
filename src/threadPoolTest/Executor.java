package threadPoolTest;

/**
 * Created by WaterMelon on 2018/4/10.
 */
public interface Executor {
    void execute(Runnable command);
}
