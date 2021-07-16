package sample.java.thread;

/**
 * 饿汉式单例模式
 * class Single{
 *     private static final Single SELF = new Single();
 *     private Single(){};
 *     public static Single getInstance(){
 *         return SELF;
 *     }
 * }
 */

/**
 * 懒汉式单例模式
 * 对实例的延迟加载，在多线程加载时存在多次实例加载的存在
 */
class Single{
    private static Single SELF = null;
    private Single(){}
    public static Single getInstance(){
        if (SELF==null){ // 避免每次调用都需要对synchronized的锁状态判断，提高运行效率和性能
            synchronized (Single.class){
                if (SELF==null){
                    SELF = new Single();
                }
            }
        }
        return SELF;
    }
}

public class ThreadSafe_Single {

}
