package AopTest;

/**
 * Created by WaterMelon on 2018/5/7.
 */

/**
 * 被代理的类
 * 目标对象类
 */
public class TragetObject {

    /**
     * 目标方法（即目标操作）
     */
    public void business(){
        System.out.println("business");
    }
}
