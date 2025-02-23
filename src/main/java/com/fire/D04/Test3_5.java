package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import static com.fire.utils.Sleeper.sleep;

/**
 * 线程八锁-6
 */
public class Test3_5 {
    public static void main(String[] args) {
        Number_5 n1 = new Number_5();




        new Thread(()->{
            n1.a();

        }).start();

        new Thread(()->{
            n1.b();
        }).start();


    }


}


@Slf4j
class Number_5{
    public static synchronized void a(){
        sleep(1);
        log.debug("1");
    }

    public static synchronized void b(){
        log.debug("2");
    }
}