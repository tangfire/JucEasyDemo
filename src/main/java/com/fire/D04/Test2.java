package com.fire.D04;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class Test2 {

//    static int cnt = 0;
//    static Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {
        Room room = new Room();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                room.increment();

            }
        },"t1");

        Thread t2 = new Thread(()->{
            for (int i = 0; i < 5000; i++) {
                room.decrement();

            }
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info("{}",room.getCnt());
    }


}

class Room{
    private int cnt = 0;

    public void increment(){
        synchronized (this){
            cnt++;
        }
    }

    public void decrement(){
        synchronized (this){
            cnt--;
        }
    }

    public int getCnt(){
        synchronized (this){
            return cnt;
        }
    }
}
