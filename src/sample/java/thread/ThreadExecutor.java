package sample.java.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Thread2 implements Runnable{
    private int countDown = 10;
    @Override
    public void run() {
        while (countDown-->0){
            System.out.println(Thread.currentThread().getName()+" 执行，countDown="+countDown);
            Thread.yield();
        }
    }
}

class ExecutorDome {
    public void executor1(){
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0;i<5;i++){
            es.execute(new Thread2());
        }
        es.shutdown();
    }
}

public class ThreadExecutor {
    public static void main(String[] args) {
        ExecutorDome exed = new ExecutorDome();
        exed.executor1();
    }
}
