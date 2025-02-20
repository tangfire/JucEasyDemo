package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {

    public static void main(String[] args) {
//        Runnable runnable = ()-> log.debug("running");
//        Thread thread = new Thread(runnable,"t3");
        Thread thread = new Thread(()->log.debug("running"),"t3");
        thread.start();
    }


}
