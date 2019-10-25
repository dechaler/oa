package za;

/**
 * @编写人:de
 * @时间:2019/9/14
 * @描述:
 */
public class ceshi<T> {
    final String a = "";//必须初值
    private T t;

    public T getT() {
        return t;
    }

    public String test(){
        if (t != null)
            return "";
        return "ss";
    }

    public <T> T get(T a){
        return a;
    }
    public static void main(String[] args) {

        Dog[][] d = new Dog[3][];
        System.out.println(d[2][0].toString());

//
//        String arr[] = new String[2];
////        String arr[2] = new String[2]; 报错
//        List<String> l = new LinkedList<>();
////        String s = "";
////        s.val
//        Calendar calendar = new GregorianCalendar();
//        List list=  new ArrayList();
//        List<String> stringList = new ArrayList<>();
//        List<Object> objectList = new ArrayList<>();
////        stringList = objectList;
////        objectList = stringList;
////        Array
//        ceshi<String> c = new ceshi<>();
//        String t = c.getT();
//        list = stringList;
//        list = objectList;
//        stringList = list;
//        objectList = list;
//
//            String s = "";
////            String.valueOf();
//        int i = s.hashCode();
//
////        List<String> aa = new ArrayList<String>();
//        HashSet set = new HashSet();
////        set.hashCode()
////        aa.add("F1");
////        aa.add("F2");
//        HashMap map = new HashMap<>();
//        aa.add("F3");
////        for (String temp : aa) {
////            if ("F3".equals(temp)) {
////                aa.remove(temp);
////            }
////        }
//        Iterator<String> it = aa.iterator();
//
//        for (int i = 0; i < aa.size(); i++) {
//            if ("F3".equals(aa.get(i))) {
//                aa.remove(aa.get(i));
//                System.out.println("删除成功");
//            }
//        }
//
//
//        for (String temp : aa){
//            System.out.println(temp);
//        }
//        add(1l);


    }

    public static void add(int a){
        System.out.println("形参为int");
    }
    public static void add(long a){
        System.out.println("形参为long");

    }

}
