package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("running...");
            }
        };

        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());

        Thread.sleep(500);
        System.out.println(t1.getState());

        log.debug("do other things...");

    }


}
