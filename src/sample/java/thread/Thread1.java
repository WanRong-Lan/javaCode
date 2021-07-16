package sample.java.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程写法1
 * 调用：new Dome().start;
 */
class Dome extends Thread{
    public void run(){
        for(int i =0;i<=60;i++){
            System.out.println(this.getName() + " run -- "+i );
        }
    }
}

/**
 * 线程方法写法2
 * 调用：new Thread(new Dome1())
 */
class Dome1 implements Runnable{
    private static int tick = 10;
    Object obj = new Object();
    @Override
    public void run() {
        while (true){
            Dome1.test();
            synchronized(obj){
                if(tick>=0){
                    try{
                        // 模拟资源被抢占
                        Thread.sleep(10);
                    }catch (Exception e){}
                    System.out.println(Thread.currentThread().getName() + " run tick: "+tick-- );
                }else{
                    break;
                }
            }
        }
    }
    public synchronized static void test(){
        if(tick>=0){
            try{
                // 模拟资源被抢占
                Thread.sleep(10);
            }catch (Exception e){}
            System.out.println(Thread.currentThread().getName() + " test tick: "+tick-- );
        }
    }
}

class Dome2 implements Runnable{
    private int tick = 10;
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try{
                // 上锁
                lock.lock();
                if(tick<0){
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " run tick: "+tick-- );

            }catch (Exception e){
                e.printStackTrace();
                lock.unlock();
            }finally {
                // 解锁
                lock.unlock();
                try{
                    // 模拟资源被抢占
                    Thread.sleep(10);
                }catch (Exception e){}
            }
        }
        System.out.println(Thread.currentThread().getName()+"结束");
    }
}
public class Thread1 {

    public static void main(String[] args) {
        Dome2 dome1 = new Dome2();
        new Thread(dome1).start();
        new Thread(dome1).start();
        new Thread(dome1).start();
    }
}
