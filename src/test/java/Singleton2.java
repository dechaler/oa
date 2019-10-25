/**
 * @编写人:de
 * @时间:2019/9/27
 * @描述:静态内部类的单例
 */
public class Singleton2 {

    //静态内部类
    private static class SingletonHolder {
        private static Singleton2 singleton2 = new Singleton2();
    }
        private Singleton2() {
        }

        public static Singleton2 getSingleton2(){
            return SingletonHolder.singleton2;
        }
    }

