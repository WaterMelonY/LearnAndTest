package threadPoolTest;

/**
 * Created by WaterMelon on 2018/4/10.
 */
public class ThreadPerTaskExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
