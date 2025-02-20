package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMultiThread {
    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                log.debug("running");
            }
        },"t1").start();

        new Thread(()->{
            while(true){
                log.debug("running");
            }
        },"t2").start();
    }
}
