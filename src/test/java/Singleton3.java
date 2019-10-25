/**
 * @编写人:de
 * @时间:2019/9/27
 * @描述:饿汉式单例模式
 */
public class Singleton3 {
    private static final Singleton3 singleton3 = new Singleton3();

    private Singleton3(){}

    public static Singleton3 getSingleton(){
        return singleton3;
    }
}
