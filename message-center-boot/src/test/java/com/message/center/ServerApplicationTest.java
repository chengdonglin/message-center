package com.message.center;

import com.message.center.infrastructure.delay.DelayTaskProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @Author linchengdong
 * @Date 2024-08-12 14:15
 * @PackageName:com.message.center
 * @ClassName: ServerApplicationTest
 * @Description: TODO
 * @Version 1.0
 */
@SpringBootTest
public class ServerApplicationTest {

    @Autowired
    private DelayTaskProcessor delayTaskProcessor;
    @Test
    public void test1() throws InterruptedException {
        //创建一个延迟任务处理器，容量是1000，也就是最多可以有1000个待处理的延迟任务
        //放入5个待处理的延迟任务，延迟时间分别是：5s,4s,3s,2s,1s
        for (int i = 5; i >= 1; i--) {
            //当前时间
            long curTimeTime = System.currentTimeMillis();
            //延迟时间
            long delayTime = i;
            //延迟时间单位
            TimeUnit delayTimeUnit = TimeUnit.SECONDS;
            String task = "task-" + i;
            //调用delayTaskProcessor.put放入需要处理的任务（延迟时间、延迟时间单位、任务、任务消费者）
            boolean putSuccess = delayTaskProcessor.put(delayTime, delayTimeUnit, () -> {
                //任务实际处理的时间
                long actualTime = System.currentTimeMillis();
                System.out.println("处理任务：" + task +
                        "，任务延迟时间(ms)：" + TimeUnit.MILLISECONDS.convert(delayTime, delayTimeUnit) +
                        "，任务实际处理的时间 - 任务放入的时间 (ms)：" + (actualTime - curTimeTime));
            });

            System.out.println(task + ":" + (putSuccess ? "放入任务成功" : "放入任务失败"));
        }
        //这里等10秒，等所有的延迟任务处理完毕
        TimeUnit.SECONDS.sleep(10);
        //停止延迟任务处理器
        delayTaskProcessor.shutdown();
    }
}
