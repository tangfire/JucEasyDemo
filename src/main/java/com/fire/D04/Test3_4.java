package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import static com.fire.utils.Sleeper.sleep;

/**
 * 线程八锁-5
 */
public class Test3_4 {
    public static void main(String[] args) {
        Number_4 n1 = new Number_4();
        new Thread(()->{
            n1.a();

        }).start();

        new Thread(()->{
            n1.b();
        }).start();


    }


}


@Slf4j
class Number_4{
    public static synchronized void a(){
        sleep(1);
        log.debug("1");
    }

    public synchronized void b(){
        log.debug("2");
    }
}