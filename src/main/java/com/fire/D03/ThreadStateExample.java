package com.fire.D03;

public class ThreadStateExample {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程但未启动
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000); // 模拟线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ThreadStateExample.class) {
                // 模拟线程进入BLOCKED状态
            }
        });

        // NEW 状态
        System.out.println("线程状态: " + thread.getState());

        // 启动线程
        thread.start();

        // RUNNABLE 状态
        System.out.println("线程状态: " + thread.getState());

        // 模拟线程进入TIMED_WAITING状态
        Thread.sleep(100);
        System.out.println("线程状态: " + thread.getState());

        // 模拟线程进入BLOCKED状态
        synchronized (ThreadStateExample.class) {
            Thread.sleep(100);
            System.out.println("线程状态: " + thread.getState());
        }

        // 等待线程执行完毕
        thread.join();

        // TERMINATED 状态
        System.out.println("线程状态: " + thread.getState());


        Thread t2 = new Thread(() -> {
            while(true){

            }
        });
        t2.start();

        Thread t5 = new Thread(){
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t5.start();
        Thread.sleep(500);
        System.out.println("t5线程状态: " + t5.getState());
    }
}