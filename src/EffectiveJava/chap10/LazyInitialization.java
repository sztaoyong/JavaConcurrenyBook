package EffectiveJava.chap10;

/**
 * Created by taoyong on 6/27/16.
 */
public class LazyInitialization {

    // lazy initialization holder class idiom. (static class)
    // https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    private static class FieldHolder {
        //the class initialization phase is guaranteed by the JLS to be serial, i.e., non-concurrent.
        static final Integer field = Integer.valueOf(1);
    }

    static Integer getField() {
        return FieldHolder.field;
    }

    public static void main(String[] args) {

        LazyInitialization test = new LazyInitialization();

        System.out.println("do nothing here");

        System.out.println("print" + LazyInitialization.getField());
    }

    // double-check idiom

    // volatile is important
    private volatile Integer i;

    public Integer getI() {
        Integer result = i;
        // 1st check: no lock needs.
        if (result == null) {
            // if context switched here, will need double check again in the sync block.
            synchronized (this) {
                result = i; // i is volatile so every change on i will be visible to read operation happened-after.
                if (result == null) { // second check with locking to avoid repeated creation.
                    result = i = Integer.valueOf(1);
                }
            }
        }
        return result;
    }
}
