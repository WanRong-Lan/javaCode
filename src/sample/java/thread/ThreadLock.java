package sample.java.thread;

/**
 * 死锁产生的原因
 */

// 线程锁资源
class MyLock{
    static Object lock1=new Object();
    static Object lock2=new Object();

}
class Test implements Runnable{
    private boolean flag = true;

    public Test(boolean flag) {
        this.flag = flag;
    }
    @Override
    public void run() {
        if(flag){
            synchronized (MyLock.lock1){
                System.out.println("if MyLock.lock1");
                synchronized (MyLock.lock2){
                    System.out.println("if MyLock.lock2");
                }
            }
        }else{
            synchronized (MyLock.lock2){
                System.out.println("else MyLock.lock2");
                synchronized (MyLock.lock1){
                    System.out.println("else MyLock.lock1");
                }
            }
        }
    }
}
public class ThreadLock
{


    public static void main(String[] args) {
        Thread t1 = new Thread(new Test(true));
        Thread t2  = new Thread(new Test(false));
        t1.start();
        t2.start();
    }
}
