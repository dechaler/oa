package za;

/**
 * @编写人:de
 * @时间:2019/10/12
 * @描述:
 */
public class Demo {
    public static void main(String[] args) {
        Integer obj = Integer.valueOf(args[args.length-1]);
        int i = obj.intValue();
        if (args.length > 1)
            System.out.println(i);
        if (args.length > 0)
            System.out.println(i-1);
        else
        System.out.println(i-2);
    }
}
