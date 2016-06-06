package EffectiveJava.chap2;

/**
 * Created by taoyong on 5/18/16.
 */

// enum way to implement singlton.
// best way: no need to worry about serialzation.
public enum ElvisEnum implements Runnable {
    INSTANCE;

    public ElvisEnum getInstance() {
        return INSTANCE;
    }

    @Override
    public void run() {
        // Elivs do some method.
        //...
    }
}
