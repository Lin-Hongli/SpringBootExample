package com.example.test;

import javax.annotation.processing.Completion;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThread {

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        executorService.execute(()-> {
            try {

                Thread.sleep(1000);
                System.out.println("123");
                AtomicInteger atomicInteger=new AtomicInteger(4);
                int andIncrement = atomicInteger.getAndIncrement();
                System.out.println(andIncrement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()-> System.out.println("456"));
        executorService.shutdown();
    }
}
