package EffectiveJava.chap2;

import java.util.Arrays;

/**
 * Created by taoyong on 5/18/16.
 */
// this problem is normally hard to notice.
// use debug tool such as heap profiler.
public class ObseleteReference {

/*
    when will memery leak happen?

    1. when a class manage its own memory. (collections etc).
    2. Cache. (unless WeakHashMap).
    3. Listeners or other callbacks.
*/

    private Object[] elements;

    private int size = 0;
    private static final int DEFAULT_INITIAL_SIZE = 0;

    public ObseleteReference() {
        elements = new Object[DEFAULT_INITIAL_SIZE];
    }

    public void push(Object obj) {
        ensureCapacity();
        elements[size++] = obj;
    }

    public Object popBad() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        return elements[--size]; // bad because this obj has never been dereferenced.
    }

    public Object pop() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    public void ensureCapacity() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
    }
}
