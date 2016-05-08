package Chap8;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by taoyong on 4/30/16.
 */
public class DiscardOldestPolichy implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        executor.getQueue().poll(); // use the non blocking one.
        // executor service use this method for task submission. this is just to put task into work queue.
        executor.execute(r);
    }
}
