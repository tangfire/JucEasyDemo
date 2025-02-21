package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test12 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpack...");
//            log.debug("打断状态:{}",Thread.currentThread().isInterrupted());

            log.debug("打断状态:{}",Thread.currentThread().interrupted());

            LockSupport.park();// 打断标记为真的时候,park()方法失效
            log.debug("unpark...");

        },"t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }
}
