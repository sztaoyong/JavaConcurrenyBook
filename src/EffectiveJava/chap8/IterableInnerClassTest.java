package EffectiveJava.chap8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by taoyong on 6/5/16.
 */
public class IterableInnerClassTest {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        IterableInnerClass<Integer> list = new IterableInnerClass(arr);

        Iterator<Integer> iterator1 = list.iterator();
        Iterator<Integer> iterator2 = list.iterator();

        for (; iterator1.hasNext(); ) {
            System.out.println("i1 = " + iterator1.next());
        }

        for (; iterator2.hasNext(); ) {
            System.out.println("i2 = " + iterator2.next());
        }
    }
}
