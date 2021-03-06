//package co.yixiang.redisson;
//
//import org.redisson.api.RBlockingQueue;
//import org.redisson.api.RDelayedQueue;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by kl on 2018/7/20.
// * Content :延时job服务
// */
//@Component
//public class DelayJobService {
//
//    @Autowired
//    private RedissonClient client;
//
//    /**
//     * 添加超时任务到redis队列
//     * @param job  任务
//     * @param delay 超时时间
//     */
//    public void submitJob(DelayJob job, Long delay){
//        RBlockingQueue<DelayJob> blockingQueue = client.getBlockingQueue(JobTimer.jobsTag);
//        RDelayedQueue<DelayJob> delayedQueue = client.getDelayedQueue(blockingQueue);
//        delayedQueue.offer(job,delay,TimeUnit.SECONDS);
//    }
//
//}