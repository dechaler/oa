/**
 * @编写人:de
 * @时间:2019/9/27
 * @描述:测试单例
 */
public class TestSingleton {
    public static void main(String[] args) {

        Singleton s1 = Singleton.getSingleton();
        Singleton s2 = Singleton.getSingleton();
        System.out.println(s1);
        System.out.println(s2);
        Singleton2  s3 = Singleton2.getSingleton2();
        Singleton2 s4 = Singleton2.getSingleton2();
        System.out.println(s3);
        System.out.println(s4);
        Singleton3 s5= Singleton3.getSingleton();
        Singleton3 s6= Singleton3.getSingleton();
        System.out.println(s5);
        System.out.println(s6);
    }
    }

