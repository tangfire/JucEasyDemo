package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test2 {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };

        Thread thread = new Thread(runnable,"t2");
        thread.start();
        log.debug("running");
    }

}
