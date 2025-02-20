package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test9 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleep...");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"t1");

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        log.debug("interrupt");
        t1.interrupt();
        log.debug("打断标记:{}",t1.isInterrupted());

    }
}
