package sample.java.thread;

/**
 * 当线程处于冻结状态。（wait）
 * 就不会读取到标记。那么线程就不会结束。
 * 当没有指定的方式让冻结的线程恢复到运行状态时，这时需要对冻结状态进行清除。
 * 强制让线程恢复到运行状态中来。这样才可以操作标记让线程结束。
 *
 * Thread 类提供了interrupt方法
 */
public class ThreadStopDemo {
    public static void main(String[] args) {
        StopThread st = new StopThread();
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        // setDaemon 线程守护,将线程标记为线程守护，当正在运行的线程都是守护线程时，Java虚拟机退出。
        // 该方法必须在启动线程前调用
        // 该方法首先调用该线程的checkAccess 方法。且不带参数。
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        int num = 0;
        while (true){
            if(num++==60){
                // 让线程从冻结/沉睡中强制恢复运行。
//                t1.interrupt();
//                t2.interrupt();
                break;
            }
            System.out.println(Thread.currentThread().getName()+".....main");
        }
    }
}

class StopThread implements Runnable{
    // 线程执行标记
    private boolean flag = true;

    @Override
    public synchronized void run() {
        while (flag){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+".....Exception");
                // 改变标记 ，退出while 循环结束此次线程。
                flag = false;
            }
            System.out.println(Thread.currentThread().getName() + ".....run");
        }
    }
}
