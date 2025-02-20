package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test10 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("被打断了,退出循环");
                    break;
                }
            }
        },"t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        log.debug("interrupt");
        t1.interrupt();

    }
}
