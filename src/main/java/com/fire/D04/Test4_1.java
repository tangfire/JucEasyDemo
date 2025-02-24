package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Test4_1 {

    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;


    public static void main(String[] args) {
        ThreadSafe threadUnsafe = new ThreadSafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(()->{
                threadUnsafe.method1(LOOP_NUMBER);
            },"Thread"+(i+1)).start();
        }
    }
}

class ThreadSafe{

    public void method1(int loopNumber){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    private void method2(ArrayList<String> list){
        list.add("1");
    }

    private void method3(ArrayList<String> list){
        list.remove(0);
    }
}
