package threadPoolTest;

/**
 * Created by WaterMelon on 2018/4/10.
 */
public class SingleThreadTaskExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
