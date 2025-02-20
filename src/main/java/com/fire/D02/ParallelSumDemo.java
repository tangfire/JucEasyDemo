package com.fire.D02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class ParallelSumDemo {
    private static final Logger logger = LoggerFactory.getLogger(ParallelSumDemo.class);
    private static final long TOTAL_COUNT = 100_000_000L;

    // 单线程计算
    public static long singleThreadSum() {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (long i = 1; i <= TOTAL_COUNT; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        logger.info("单线程计算总耗时: {} ms, 结果: {}", (end - start), sum);
        return sum;
    }

    // 多线程计算
    public static long multiThreadSum() throws InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        logger.info("可用处理器核心数: {}", processors);

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);
        LongAdder finalSum = new LongAdder();
        CountDownLatch latch = new CountDownLatch(processors);

        long segmentSize = TOTAL_COUNT / processors;

        for (int i = 0; i < processors; i++) {
            long start1 = i * segmentSize + 1;
            long end1 = (i + 1) * segmentSize;
            
            // 处理最后一个线程可能的边界情况
            if (i == processors - 1) {
                end1 = TOTAL_COUNT;
            }

            final long threadStart = start1;
            final long threadEnd = end1;

            executorService.submit(() -> {
                long threadSum = 0;
                for (long j = threadStart; j <= threadEnd; j++) {
                    threadSum += j;
                }
                finalSum.add(threadSum);
                latch.countDown();
            });
        }

        latch.await(); // 等待所有线程完成
        executorService.shutdown();

        long end = System.currentTimeMillis();
        logger.info("多线程计算总耗时: {} ms, 结果: {}", (end - start), finalSum.longValue());
        return finalSum.longValue();
    }

    public static void main(String[] args) throws InterruptedException {
        // 预热JVM
        for (int i = 0; i < 3; i++) {
            singleThreadSum();
            multiThreadSum();
        }

        // 正式测试
        logger.info("开始性能测试");
        
        // 单线程计算
        long singleThreadStart = System.currentTimeMillis();
        long singleThreadResult = singleThreadSum();
        long singleThreadEnd = System.currentTimeMillis();

        // 多线程计算
        long multiThreadStart = System.currentTimeMillis();
        long multiThreadResult = multiThreadSum();
        long multiThreadEnd = System.currentTimeMillis();

        // 验证结果一致性
        logger.info("单线程结果: {}", singleThreadResult);
        logger.info("多线程结果: {}", multiThreadResult);
        logger.info("结果是否一致: {}", singleThreadResult == multiThreadResult);

        // 性能对比
        logger.info("单线程总耗时: {} ms", singleThreadEnd - singleThreadStart);
        logger.info("多线程总耗时: {} ms", multiThreadEnd - multiThreadStart);
    }
}