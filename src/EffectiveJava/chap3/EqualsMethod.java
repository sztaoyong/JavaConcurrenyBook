package EffectiveJava.chap3;

/**
 * Created by taoyong on 5/22/16.
 */
public class EqualsMethod {

    public static void main(String[] args) {
        float a = Float.NaN;
        float b = Float.NaN;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("a == b ? " + (a == b));
        System.out.println("Float.compare(a, b)? " + Float.compare(a, b));


        float c = 0.0f;
        float d = -0.0f;
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        System.out.println("c == d ? " + (c == d));
        System.out.println("Float.compare(c, d)? " + Float.compare(c, d));
    }
}
