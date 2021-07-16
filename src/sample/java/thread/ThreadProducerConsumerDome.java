package sample.java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 结合 ThreadInputOutPutDome.class 在有2个以上线程以上是容易出现Input和Output
 *
 * 生成者--消费者  ----  显式锁机制
 * 通过condition 操作唤醒和沉睡指定的线程。
 *
 * 实现了将同步 synchronized 函数替换成实现Lock 操作。
 * 由原来的Object中的wait、notify、notifyAll 替换成了condition对象。
 * 优化了唤醒全部线程导致的无用资源的消耗，指定了生产者线程或消费者线程 只唤醒对方。
 *
 * 在该示例中，实现了本方只唤醒对方的操作
 */
public class ThreadProducerConsumerDome {

    public static void main(String[] args) {
        Resoutce res = new Resoutce();
        // 生产者线程
        Thread t1 = new Thread(new Producer(res));
        Thread t2 = new Thread(new Producer(res));
        t1.start();
        t2.start();

        // 消费者线程
        Thread t3 = new Thread(new Consumer(res));
        Thread t4 = new Thread(new Consumer(res));
        t3.start();
        t4.start();


    }
}

// 资源
class Resoutce{
    private String name;
    private int count = 1;
    private boolean flag = false;
    private Lock lock = new ReentrantLock();

    // 生产者 condition
    private Condition condition_pro = lock.newCondition();
    // 消费者 condition
    private Condition condition_com=lock.newCondition();

    public  void setInfo(String name) throws InterruptedException {
        // 获取锁
        lock.lock();
        try{
            // 没有while 判断，和notifyAll 唤醒所有线程 则所有线程就会等待
            while (flag)
                // 生产者要等待
                condition_pro.await();

            this.name="产品名称："+name+"，产品编号："+count++;
            System.out.println(Thread.currentThread().getName()+":生成："+this.name);
            this.flag=true;
            // 唤醒一个消费者
            condition_com.signal();
        }finally {
            // 必须执行的，所以加入到了finally中
            lock.unlock();// 解锁
        }

    }
    public void outInfo() throws InterruptedException {
        lock.lock();
        try{
            while (!flag)
                // 消费者等待
                condition_com.await();
            System.out.println(Thread.currentThread().getName()+":消费......"+this.name);
            this.flag=false;
            // 唤醒一个生产者
            condition_pro.signal();
        }finally {
            lock.unlock();
        }


    }
}

// 生产者
class Producer implements Runnable{
    private Resoutce res;

    public Producer(Resoutce res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            try {
                this.res.setInfo("商品商品");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 消费者
class Consumer implements Runnable{
    private Resoutce res;

    public Consumer(Resoutce res) {
        this.res = res;
    }
    @Override
    public void run() {
        while (true){
            try {
                this.res.outInfo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


