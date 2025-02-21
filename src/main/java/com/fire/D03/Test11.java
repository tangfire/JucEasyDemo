package com.fire.D03;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Test11 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        Thread.sleep(3500);
        tpt.stop();

    }
}

@Slf4j
class TwoPhaseTermination{
    private Thread monitor;

    // 启动监控线程
    public void start(){
        monitor = new Thread(()->{
            while(true){
                Thread thread = Thread.currentThread();
                if (thread.isInterrupted()){
                    log.debug("料理后事");
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    log.debug("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    thread.interrupt();
                }

            }
        });
        monitor.start();
    }

    // 停止监控线程
    public void stop(){
        monitor.interrupt();
    }



}
