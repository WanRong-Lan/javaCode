package sample.java.thread;
// 线程数据通信
class People{
    private String name;
    private String sex;

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
                    this.people.setName("丽丽");
                    this.people.setSex("女");
                }else{
                    this.people.setName("亮亮");
                    this.people.setSex("男");
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
                System.out.println(this.people.toString());
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
