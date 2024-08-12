package com.message.center.infrastructure.delay;

import org.springframework.beans.factory.DisposableBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author linchengdong
 * @Date 2024-08-12 11:29
 * @PackageName:com.message.center.infrastructure.delay
 * @ClassName: DelayTaskProcessor
 * @Description: TODO
 * @Version 1.0
 */
public class DelayTaskProcessor implements Runnable, DisposableBean {
    //名称，标识作用
    private String name;
    //容量
    private int capacity;

    private int concurrency;

    //延迟队列
    private DelayQueue<DelayTask> delayQueue = new DelayQueue<>();

    //是否已停止
    private volatile boolean stop = false;

    //当前待处理的任务数量
    private AtomicInteger size = new AtomicInteger(0);

    private List<Thread> consumerThreadList;

    /**
     * 延迟任务执行器
     *
     * @param name        名称
     * @param capacity    容量
     * @param concurrency 消费者个数（即开启几个线程去处理任务）
     */
    public DelayTaskProcessor(String name, int capacity, int concurrency) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        if (concurrency <= 0) {
            throw new IllegalArgumentException("Illegal Concurrency: " + concurrency);
        }
        this.name = name;
        this.capacity = capacity;
        this.concurrency = concurrency;

        //创建 concurrency 个消费者线程，并启动，消费者线程会从延迟队列拉取到期的任务进行消费
        this.consumerThreadList = new ArrayList<>(this.concurrency);
        for (int i = 0; i < this.concurrency; i++) {
            String consumerThreadName = this.generateConsumerThreadName(i);
            Thread consumerThread = new Thread(this, consumerThreadName);
            consumerThread.start();
            this.consumerThreadList.add(consumerThread);
        }
    }

    /**
     * 生成消费者线程名称
     *
     * @param consumerIndex
     * @return
     */
    protected String generateConsumerThreadName(int consumerIndex) {
        return String.format("DelayTaskProcessor-ConsumerThread-%s-%s", name, consumerIndex);
    }

    /**
     * 放入要执行的延迟任务
     *
     * @param taskExecuteTimeMs 任务什么时候执行（时间戳毫秒），比如：延迟5分钟后执行，则 taskExecuteTimeMs = System.currentTimeMillis() + 5*60*1000
     * @param task              任务
     * @return 超过容量后会返回false
     */
    public boolean put(long taskExecuteTimeMs, Runnable task) {
        if (size.get() == capacity) {
            return false;
        }
        synchronized (this) {
            //当前待处理的任务数量达到了容量，返回false
            if (size.get() == capacity) {
                return false;
            }
            //放入延迟队列
            this.delayQueue.put(new DelayTask(taskExecuteTimeMs, task));
            //待处理任务数+1
            size.incrementAndGet();
        }
        return true;
    }


    /**
     * 放入要执行的延迟任务
     *
     * @param delayTime     延迟时间
     * @param delayTimeUnit 延迟时间单位
     * @param task          任务
     * @return 超过容量后会返回false
     */
    public boolean put(long delayTime, TimeUnit delayTimeUnit, Runnable task) {
        long taskExecuteTimeMs = System.currentTimeMillis() + delayTimeUnit.toMillis(delayTime);
        return this.put(taskExecuteTimeMs, task);
    }


    @Override
    public void run() {
        while (true) {
            if (stop) {
                break;
            }
            Runnable task = null;
            try {
                //从延迟队列中拿到任务
                task = delayQueue.take().getTask();
                //容量--
                size.decrementAndGet();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (task == null) {
                continue;
            }
            this.taskDispose(task);
        }
    }

    /**
     * 处理任务
     *
     * @param task
     */
    private void taskDispose(Runnable task) {
        Throwable throwable = null;
        try {
            //处理任务
            task.run();
        } catch (Throwable e) {
            throwable = e;
        } finally {
            this.afterExecute(task, throwable);
        }
    }

    public static class DelayTask<T> implements Delayed {

        // 延迟任务执行时间（时间戳毫秒）
        private long taskExecuteTimeMs;
        private Runnable task;

        public DelayTask(long taskExecuteTimeMs, Runnable task) {
            this.taskExecuteTimeMs = taskExecuteTimeMs;
            this.task = task;
        }

        public void setTaskExecuteTimeMs(long taskExecuteTimeMs) {
            this.taskExecuteTimeMs = taskExecuteTimeMs;
        }

        public Runnable getTask() {
            return task;
        }

        public long getTaskExecuteTimeMs() {
            return taskExecuteTimeMs;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(taskExecuteTimeMs - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o instanceof DelayTask) {
                DelayTask o1 = (DelayTask) o;
                return Long.compare(this.getTaskExecuteTimeMs(), o1.getTaskExecuteTimeMs());
            }
            return 0;
        }

    }

    public void shutdown() {
        this.destroy();
    }

    @Override
    public void destroy() {
        this.stop = true;
        for (Thread consumerThread : consumerThreadList) {
            consumerThread.interrupt();
        }
    }

    /**
     * 任务执行完成后回调
     *
     * @param task 任务
     * @param t    异常信息
     */
    protected void afterExecute(Runnable task, Throwable t) {
    }

    public AtomicInteger getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "DelayTaskProcessor{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", concurrency=" + concurrency +
                ", size=" + size +
                '}';
    }
}
