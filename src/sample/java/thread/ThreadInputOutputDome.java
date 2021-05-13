package sample.java.thread;
// 线程数据通信

/**
 * 线程等待唤醒机制
 * --------------------
 * wait、notify、notifyAll
 *  都使用在同步中的，因为对持有监视锁的线程操作，所以要在同步中。
 *  因为只有同步才具有锁。
 *
 *  为什么这些线程操作的方法定义在Object类中？
 *  因为这些方法在操作同步线程时，
 *  都必须要表示它们所操作线程只有的锁，
 *  只有同一个锁上的被等待线程，可以被同一个锁上的notify唤醒。
 *  不可以对不同锁中的线程进行唤醒。
 *  简单来锁，等待和唤醒必须是同一个锁。
 *  而锁可以是任意对象，所以可以被任意对象调用的方法定义Object类
 *  ------------------
 */
class People{
    private String name;
    private String sex;
    // 执行标记，false 为写入，true为输出
    private Boolean flag = false;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
    public synchronized void setInfo(String name,String sex){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name= name;
        this.sex = sex;
        this.flag = true;
        try {
            this.notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public synchronized void outPut(){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.toString());
        this.flag=false;
        try {
            this.notify();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Input implements Runnable{
    private People people;
    public Input(People people) {
        this.people = people;
    }


    @Override
    public void run() {
        int i = 0;
        while (true){
            synchronized (this.people){
                if(i%2!=0){
                    this.people.setInfo("丽丽","女");
                }else{
                    this.people.setInfo("亮亮","男");
                }
                i++;
            }

        }
    }
}
class Output implements Runnable{
    private People people;

    public Output(People people) {
        this.people = people;
    }

    @Override
    public void run() {
        while (true){
            synchronized (this.people){
                this.people.outPut();
            }
        }
    }
}

public class ThreadInputOutputDome {

    public static void main(String[] args) {
        People p = new People();
        Thread t1 = new Thread(new Input(p));
        Thread t2 = new Thread(new Output(p));
        t1.start();
        t2.start();
    }
}
