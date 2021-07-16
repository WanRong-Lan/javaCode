package sample.java.thread;

/**
 * 问题：打印出现不同线程打印出同一个数字、或出现-1，-2的现象
 *这表现就是线程安全
 * 问题原因：

 * 解决办法
 *      对多条操作共享数据的语句，只能让一个线程都执行完，在执行过程中其他线程不可以参与执行。
 *      可以采用Java提供的专业解决方式，同步代码块
 *      synchronized (Object){需要被同步的代码块}
 *
 * synchronized (Object) 同步代码块的原理，
 *      Object 如同锁，持有锁的线程可以在同步中执行。
 *      没有持有的线程即使获取CPU执行权限也进不去。
 *      这就好比一个房间只能容纳一个人工作，进去一个人将门反锁上避免其他人来抢占房间。而一个人占用一个房间资源工作是比较耗费资源的。
 * 同步函数和同步代码块
 *      同步函数是将synchronized 作为函数修饰符使用的锁的当前函数的this，同步代码块是将函数中部分代码视为同步代码。
 *      同步函数被静态修饰后，使用的锁不是this，因为静态方法中不可以定义this，而是使用的该方法所在的类名.class
 * 同步的前提
 *      1. 必须要有两个或两个以上的线程
 *      2. 必须是多线程使用同一个锁
 * 好处：解决多线程的安全问题
 * 弊端：多线程需要判断锁，比较消耗资源。
 *
 *
 */

class Ticket implements  Runnable{
    private static int tick = 100;
    Object obj = new Object();
    @Override
    public void run() {
        while (true){
            // 添加同步代码块 synchronized(对象)，
            synchronized (obj){
                //去掉synchronized(对象)执行以下代码 会出现多个窗口同时卖出同一张票或出现负数ID票，导致票号溢出。
                if(tick>0){
                    // 线程沉睡，释放执行权给其他线程。就好比程序执行一半执行权被其他线程抢占，导致此线程还未执行完全。
                    try {Thread.sleep(10);}catch (Exception e){}
                    System.out.println(Thread.currentThread().getName() + "售票窗口，卖出票ID:"+tick--);
                }
            }
        }
    }
}

public class ThreadSafe {
    public static void main(String[] args) {
        Ticket tk = new Ticket();
        Thread t1 = new Thread(tk);
        Thread t2 = new Thread(tk);
        Thread t3 = new Thread(tk);
        Thread t4 = new Thread(tk);
        Thread t5 = new Thread(tk);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
