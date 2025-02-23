package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import static com.fire.utils.Sleeper.sleep;


/**
 * 线程八锁-2
 *
 */
@Slf4j
public class Test3_1 {
    public static void main(String[] args) {
        Number_1 n1 = new Number_1();
        new Thread(()->{
            log.debug("begin");
            n1.a();
        }).start();

        new Thread(()->{
            log.debug("begin");
            n1.b();
        }).start();
    }
}


@Slf4j
class Number_1{
    public synchronized void a(){
        sleep(1);
        log.debug("1");
    }

    public synchronized void b(){
        log.debug("2");
    }
}
