package com.ftmatters.crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Crawler {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {

//        BlockingQueue<Integer> unboundedQueue = new LinkedBlockingQueue<>();

        System.out.println(new Crawler().getGreeting());
    }
}
