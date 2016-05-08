package Chap8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by taoyong on 4/30/16.
 */
public class MyAppThread extends Thread{
    public static final String DEFAULT_NAME = "MyAppThread";
    private static final Logger log = Logger.getAnonymousLogger();

    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();

    public MyAppThread(Runnable r) {
        this(r, DEFAULT_NAME);
    }

    public MyAppThread(Runnable r, String name) {
        super(r, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        log.log(Level.SEVERE, "Uncaught in  thread " +  t.getName(), e);
                    }
                }
        );
    }

    public void run() {
        try{
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
        }
    }
}
