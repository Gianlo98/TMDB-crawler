package com.ftmatters.crawler;
public class Crawler {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new Crawler().getGreeting());
    }
}
