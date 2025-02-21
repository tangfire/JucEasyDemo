package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test1 {

    static int cnt = 0;
    static Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    cnt++;
                }

            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    cnt--;
                }

            }
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.debug("cnt = {}",cnt);
    }


}
