package Chap7;

import java.util.concurrent.BlockingQueue;

/**
 * Created by taoyong on 4/26/16.
 */
public class NonCancellableTask {
    BlockingQueue<String> task;

    public String getNextString() {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return task.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
                //cannot put it here. cause the thread cannot be interrupted here.
                //finally {
                //    // when Interrupted exception is thrown. the interrupted status will be cleared.
                //    if (interrupted) {
                //        Thread.currentThread().interrupt();
                //    }
                //}
            }
        } finally {
            // when Interrupted exception is thrown. the interrupted status will be cleared.
            // only perform this before return the value.
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
