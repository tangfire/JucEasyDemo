package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test13 {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            while(true){
                if (Thread.currentThread().isInterrupted()){
                    break;
                }
            }
            log.debug("结束");
        },"t1");
        t1.setDaemon(true);

        t1.start();

        Thread.sleep(1000);
        log.debug("结束");

    }
}
