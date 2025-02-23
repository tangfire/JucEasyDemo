package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程八锁-1
 *
 */
@Slf4j
public class Test3 {
    public static void main(String[] args) {
        Number n1 = new Number();
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
class Number{
    public synchronized void a(){
      log.debug("1");
    }

    public synchronized void b(){
        log.debug("2");
    }
}
