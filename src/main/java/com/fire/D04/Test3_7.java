package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import static com.fire.utils.Sleeper.sleep;

/**
 * 线程八锁-8
 */
public class Test3_7 {
    public static void main(String[] args) {
        Number_7 n1 = new Number_7();
        Number_7 n2 = new Number_7();



        new Thread(()->{
            n1.a();

        }).start();

        new Thread(()->{
            n2.b();
        }).start();

    }

}


@Slf4j
class Number_7{
    public static synchronized void a(){
        sleep(1);
        log.debug("1");
    }

    public static synchronized void b(){
        log.debug("2");
    }
}