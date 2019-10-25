/**
 * @编写人:de
 * @时间:2019/9/26
 * @描述:双重检测单例模式
 */
public class Singleton {
    private static Singleton singleton;

    //构造方法私有化
    private Singleton(){}

    //静态获取对象方法

    public static Singleton getSingleton(){
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton)
                    singleton = new Singleton();
            }
        }
        return singleton;
    }
}
