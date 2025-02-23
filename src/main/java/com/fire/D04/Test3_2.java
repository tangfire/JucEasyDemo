package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import static com.fire.utils.Sleeper.sleep;


/**
 * 线程八锁-3
 *
 */
@Slf4j
public class Test3_2 {
    public static void main(String[] args) {
        Number_2 n1 = new Number_2();
        new Thread(()->{
            log.debug("begin");
            n1.a();
        }).start();

        new Thread(()->{
            log.debug("begin");
            n1.b();
        }).start();

        new Thread(()->{
            log.debug("begin");
            n1.c();
        }).start();
    }
}


@Slf4j
class Number_2{
    public synchronized void a(){
        sleep(1);
        log.debug("1");
    }

    public synchronized void b(){
        log.debug("2");
    }

    public void c(){
        log.debug("3");
    }
}
