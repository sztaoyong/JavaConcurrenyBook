package EffectiveJava.chap10;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by taoyong on 6/26/16.
 */
public class ObservableSet<E> {
    private final Set<E> set;

    private final List<SetObserver<E>> observers = new ArrayList<>();

    public ObservableSet() {set = new HashSet<>();}

    public void addObserver(SetObserver<E> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized (observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded (E element) {
        // this sync block cedes control to an alien method;
        // which can cause mess.

        //synchronized (observers) {
        //    for (SetObserver<E> observer : observers) {
        //        observer.added(this, element);
        //    }
        //}

        // minimize synchronized block and avoid the alien method.
        List<SetObserver<E>> snapshot = null;
        synchronized (observers) {
            snapshot = new ArrayList<>(observers);
        }
        for (SetObserver<E> observer: snapshot) {
            observer.added(this, element);
        }
    }

    public boolean add(E element) {
        boolean added = set.add(element);
        if (added) {
            notifyElementAdded(element);
        }
        return added;
    }

    public static void main(String[] args) {
        ObservableSet<Integer> observableSet = new ObservableSet<>();

        observableSet.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println("Observer 1: " + element);
                if (element == 2) set.removeObserver(this);
            }
        });

        observableSet.addObserver((set, element) -> System.out.println("Observer 2: " +  element));

        for (int i = 0; i < 4; i++) {
            observableSet.add(i);
        }

        List<Integer> test = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        synchronized (test) {
            Iterator<Integer> iter = test.iterator();
            while(iter.hasNext()) {
                Integer t = iter.next();
                if (t == 1) {
                    iter.remove();
                }
            }
        }
    }
}
