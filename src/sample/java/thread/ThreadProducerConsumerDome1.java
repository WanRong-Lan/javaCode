package sample.java.thread;

/**
 * 结合 ThreadInputOutPutDome.class 在有2个以上线程以上是容易出现Input和Output 两个重复执行，打断了交替执行。
 *
 * 生成者--消费者
 */
public class ThreadProducerConsumerDome1 {
    public static void main(String[] args) {
        Resoutce1 res = new Resoutce1();
        // 生产者线程
        Thread t1 = new Thread(new Producer1(res));
        Thread t2 = new Thread(new Producer1(res));
        t1.start();
        t2.start();

        // 消费者线程
        Thread t3 = new Thread(new Consumer1(res));
        Thread t4 = new Thread(new Consumer1(res));
        t3.start();
        t4.start();


    }
}

// 资源
class Resoutce1{
    private String name;
    private int count = 1;
    private boolean flag = false;
    public  synchronized void setInfo(String name){
        // 没有while 判断，和notifyAll 唤醒所有线程 则所有线程就会等待
        while (flag)
            try{this.wait();}catch (Exception e){e.printStackTrace();}

        this.name="产品名称："+name+"，产品编号："+count++;
        System.out.println(Thread.currentThread().getName()+":生成："+this.name);
        this.flag=true;
        try{this.notifyAll();}catch (Exception e){e.printStackTrace();}
    }
    public synchronized void outInfo(){
        while (!flag)
            try{this.wait();}catch (Exception e){e.printStackTrace();}
        System.out.println(Thread.currentThread().getName()+":消费......"+this.name);
        this.flag=false;
        try{this.notifyAll();}catch (Exception e){e.printStackTrace();}
    }
}

// 生产者
class Producer1 implements Runnable{
    private Resoutce1 res;

    public Producer1(Resoutce1 res) {
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            this.res.setInfo("商品商品");
        }
    }
}

// 消费者
class Consumer1 implements Runnable{
    private Resoutce1 res;

    public Consumer1(Resoutce1 res) {
        this.res = res;
    }
    @Override
    public void run() {
        while (true){
            this.res.outInfo();
        }
    }
}


