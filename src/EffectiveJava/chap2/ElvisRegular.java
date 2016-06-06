package EffectiveJava.chap2;

import java.io.Serializable;

/**
 * Created by taoyong on 5/18/16.
 */
public class ElvisRegular implements Serializable {

    private static ElvisRegular instance = new ElvisRegular();

    public ElvisRegular getInstance() {
        return instance;
    }

    private Object readResolve() {
        return instance;
    }

}
