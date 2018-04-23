package test;

import java.util.Random;

/**
 * Created by WaterMelon on 2018/4/9.
 */
public class DataTransferByCallBack extends Thread{
    private Work work;
    public DataTransferByCallBack(Work work){
        this.work = work;
    }

    public void run(){
        Data data = new Data();
        Random random = new Random();
        int n1 = random.nextInt(1000);
        int n2 = random.nextInt(2000);
        int n3 = random.nextInt(3000);
        work.process(data,n1,n2,n3);
        System.out.println(String.valueOf(n1) + "+" + String.valueOf(n2) + "+"
                + String.valueOf(n3) + "=" + data.value);
    }

    public static void main(String[] args) {
        new DataTransferByCallBack(new Work()).start();
    }
}

class Data{
    public int value = 0;
}

class Work{
    public void process(Data data,Integer... numbers){
        for (int n :numbers){
            data.value += n;
        }
    }
}