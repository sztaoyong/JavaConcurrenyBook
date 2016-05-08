package Chap7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * an executor service that can return cancelled tasks
 */
public class TrackExecutor extends AbstractExecutorService{

    private final ExecutorService delegate;
    private final Set<Runnable> tasksCancelledAtShutdown;

    public TrackExecutor(ExecutorService exec) {
        delegate = exec;
        tasksCancelledAtShutdown = Collections.synchronizedSet(new HashSet<>());
    }

    @Override
    public void shutdown() {
        delegate.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return delegate.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return delegate.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return delegate.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return delegate.awaitTermination(timeout, unit);
    }

    public List<Runnable> getCancelledTask() {
        if (!delegate.isTerminated()) {
            throw new IllegalStateException();
        }
        return new ArrayList<>(tasksCancelledAtShutdown);
    }

    @Override
    public void execute(Runnable command) {
        delegate.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally { // use finally because we want to count all interrupted tasks. however, we don't know how
                    // task handles interruption so we don't know how to use catch-exception.

                    // this tech  nique is called instrumentation.
                    if (isShutdown() && Thread.currentThread().isInterrupted()) {
                        tasksCancelledAtShutdown.add(command);
                    }
                }
            }
        });
    }
}
