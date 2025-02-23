package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import static com.fire.utils.Sleeper.sleep;

/**
 * 线程八锁-4
 *
 */
@Slf4j
public class Test3_3 {
    public static void main(String[] args) {
        Number_3 n1 = new Number_3();
        Number_3 n2 = new Number_3();
        new Thread(()->{
            log.debug("begin");
            n1.a();
        }).start();

        new Thread(()->{
            log.debug("begin");
            n2.b();
        }).start();
    }
}


@Slf4j
class Number_3{
    public synchronized void a(){
        sleep(1);
        log.debug("1");
    }

    public synchronized void b(){
        log.debug("2");
    }
}
