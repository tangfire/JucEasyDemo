package com.fire.D04;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * 访问修饰符一定程度上可以保证方法的线程安全
 */
@Slf4j
public class Test4_2 {

    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;


    public static void main(String[] args) {
        ThreadUnsafeSubClass threadUnsafe = new ThreadUnsafeSubClass();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(()->{
                threadUnsafe.method1(LOOP_NUMBER);
            },"Thread"+(i+1)).start();
        }
    }
}

class ThreadUnsafe_2{

    /**
     * public final void method1(int loopNumber){}
     * @param loopNumber
     */
    public void method1(int loopNumber){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    /**
     * private
     * @param list
     */
    public void method2(ArrayList<String> list){
        list.add("1");
    }

    /**
     * private
     * @param list
     */
    public void method3(ArrayList<String> list){
        list.remove(0);
    }
}

class ThreadUnsafeSubClass extends ThreadUnsafe_2{
    @Override
    public void method3(ArrayList<String> list) {
        new Thread(()->{
            list.remove(0);
        }).start();
    }
}
