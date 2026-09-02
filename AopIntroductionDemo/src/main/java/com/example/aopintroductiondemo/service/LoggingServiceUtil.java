package com.example.aopintroductiondemo.service;

public class LoggingServiceUtil {
     public static void logStart(String className, String methodName) {
        System.out.println("Executing -> " + className + " : " + methodName);
    }

    public static void logEnd(String className, String methodName) {
        System.out.println("Finishing -> " + className + " : " + methodName);
    }

}
