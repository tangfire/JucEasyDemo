package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class Test4 {

    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;


    public static void main(String[] args) {
        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(()->{
                threadUnsafe.method1(LOOP_NUMBER);
            },"Thread"+(i+1)).start();
        }
    }
}

class ThreadUnsafe{
    ArrayList<String> list = new ArrayList<>();
    public void method1(int loopNumber){
        for (int i = 0; i < loopNumber; i++) {
            method2();
            method3();
        }
    }

    private void method2(){
        list.add("1");
    }

    private void method3(){
        list.remove(0);
    }
}
