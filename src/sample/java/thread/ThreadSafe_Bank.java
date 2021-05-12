package sample.java.thread;

/**
 * 练习题：实现银行存款取款
 * 需求：
 * 银行：银行需要资金池
 * 银行营业部：接口储户
 * 业务受理窗口：受理储户业务
 * 储户：存取钱
 * 要求：保证资金池的资金正确
 * 需要解决问题：
 * 1. 需要找出哪些操作是多线程运行的。储户、业务受理窗口、营业部、银行
 * 2. 共享数据：资金池
 */
class Bank{
    //  资金池和锁，静态变量确保只有一个资金池和一个锁。
    private static int sum=0;
    private static Object obj = new Object();
    // 存钱
    public synchronized void add(int m, String ThreadId,int dep) {
//        synchronized(obj){
            System.out.println("窗口："+ThreadId+"，储户："+dep+",存钱："+m);
            sum = sum+m;
            this.saySum();
//        }

    }
    // 取钱
    public synchronized void minus(int m, String ThreadId,int dep){
//        synchronized (obj){
            System.out.println("窗口："+ThreadId+"，储户："+dep+",取钱钱："+m);
            if(sum-m>0){
                sum = sum-m;
                this.saySum();
            }else{
                System.out.println("资金池余额不足，剩余资金："+sum);
            }
//        }
    }
    // 资金总额
    public void saySum(){
        System.out.println("资金池总额："+sum);
    }
}
// 银行营业部
class BankDep implements Runnable{
    // 营业部单次操作最大次数
    private int max = 500;
    // 生成资金池
    Bank bank = new Bank();
    // 创建操作锁
    Object obj = new Object();
    @Override
    public void run() {

        while (true){
            if(max<=0){
                break;
            }
            // 定义储户,0存钱、1取钱
            int k = (int) (Math.random()*(10)+1);
            // 储户操作的金额
            int money = (int) (Math.random()*(100)+1);
            // 模拟其他窗口也在接待客户
            try {
                Thread.sleep(10);
            }catch (Exception e){}

            if (k%2==0){
                bank.add(money,Thread.currentThread().getName(),k);
            }else{
                bank.minus(money,Thread.currentThread().getName(),k);
            }
            max--;

        }

    }
}

public class ThreadSafe_Bank {
    public static void main(String[] args) {
        // 创建营业部
        BankDep bd = new BankDep();
        BankDep hd1 = new BankDep();
        // 创建业务受理窗口1,2,3,4
        Thread t1 = new Thread(bd);
        Thread t2 = new Thread(bd);
        Thread t3 = new Thread(hd1);
        Thread t4 = new Thread(hd1);
        // 窗口开始受理
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
