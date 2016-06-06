package EffectiveJava.chap8;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by taoyong on 6/5/16.
 */
public class IterableInnerClass<T> implements Iterable<T> {
    List<T> delegate;

    public IterableInnerClass(List<T> t) {
        delegate = t;
    }

    public class IterableInnerClassIterator<T> implements Iterator<T> {
        int i = 0;


        public boolean hasNext() {
            return i < delegate.size();
        }

        public T next() {
            T element = (T) delegate.get(i++);
            return element;
        }
    };

    @Override
    public Iterator<T> iterator() {
        return new IterableInnerClassIterator();
    }
}
