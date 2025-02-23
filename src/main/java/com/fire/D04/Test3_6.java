package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import static com.fire.utils.Sleeper.sleep;

/**
 * 线程八锁-7
 */
public class Test3_6 {
    public static void main(String[] args) {
        Number_6 n1 = new Number_6();
        Number_6 n2 = new Number_6();

        new Thread(()->{
            n1.a();

        }).start();

        new Thread(()->{
            n2.b();
        }).start();


    }


}


@Slf4j
class Number_6{
    public static synchronized void a(){
        sleep(1);
        log.debug("1");
    }

    public synchronized void b(){
        log.debug("2");
    }
}