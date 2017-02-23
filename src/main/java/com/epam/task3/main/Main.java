package com.epam.task3.main;

import com.epam.task3.controller.Controller;

public class Main {
    private final static String ADD = "add_news film,asd,das";
    private final static String GET_NEWS = "get_news film,,";

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.initResource();
        System.out.println(controller.executeTask(ADD));
        controller.clearResource();
    }
}