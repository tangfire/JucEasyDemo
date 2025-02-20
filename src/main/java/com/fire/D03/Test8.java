package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test8 {
    public static void main(String[] args) {
        Runnable task1 = ()->{
            int cnt = 0;
            while(true){
                System.out.println("----->1 " + cnt++);
            }
        };
        Runnable task2 = ()->{
            int cnt = 0;
            while(true){
                Thread.yield();
                System.out.println("        ----->2 " + cnt++);
            }
        };
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
//        t1.setPriority(Thread.MAX_PRIORITY);
//        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }
}
