package sample.java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 结合 ThreadInputOutPutDome.class 在有2个以上线程以上是容易出现Input和Output 两个重复执行，打断了交替执行。
 *
 * 生成者--消费者
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
    private Condition condition = lock.newCondition();
    public  void setInfo(String name) throws InterruptedException {
        // 获取锁
        lock.lock();
        try{
            // 没有while 判断，和notifyAll 唤醒所有线程 则所有线程就会等待
            while (flag)
                condition.await();

            this.name="产品名称："+name+"，产品编号："+count++;
            System.out.println(Thread.currentThread().getName()+":生成："+this.name);
            this.flag=true;
            // 唤醒一个等待线程
            condition.signal();
        }finally {
            // 必须执行的
            lock.unlock();// 解锁
        }

    }
    public void outInfo() throws InterruptedException {
        lock.lock();
        try{
            while (!flag)
                condition.await();
            System.out.println(Thread.currentThread().getName()+":消费......"+this.name);
            this.flag=false;
            condition.signal();
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


