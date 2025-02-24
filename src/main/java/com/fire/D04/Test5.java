package com.fire.D04;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * 卖票
 */
@Slf4j
public class Test5 {
    public static void main(String[] args) {
        TicketWindow ticketWindow = new TicketWindow(10000);

        List<Thread> threadList = new ArrayList<>();

        List<Integer> amountList = new Vector<Integer>();

        for (int i = 0; i < 4000; i++) {
            Thread thread = new Thread(() -> {
                int amount = ticketWindow.sell(randomAmount());
                amountList.add(amount);
            });
            threadList.add(thread);
            thread.start();

        }

        threadList.forEach(t ->{
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        log.debug("余额:{}",ticketWindow.getCount());
        log.debug("卖出的票数:{}",amountList.stream().mapToInt(i -> i).sum());
    }

    static Random random = new Random();

    public static int randomAmount(){
        return random.nextInt(5)+1;
    }
}

class TicketWindow{
    private int count;

    public TicketWindow(int count){
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public synchronized int sell(int amount){
        if(this.count >= amount){
            this.count -= amount;
            return amount;
        }else{
            return 0;
        }
    }
}
