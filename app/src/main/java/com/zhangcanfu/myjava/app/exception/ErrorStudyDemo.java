package com.zhangcanfu.myjava.app.exception;

import com.zhangcanfu.myjava.app.exception.model.ConfigurationError;

import java.io.FileNotFoundException;

public class ErrorStudyDemo {

    public static void main(String[] args) {
        showErrorIsThrowable();
        showErrorIsUnchecked();
        showCatchIsPossibleButRare();
    }

    private static void showErrorIsThrowable() {
        System.out.println("=== 1. Error is a Throwable ===");
        Error error = new Error("A serious JVM-level or application-level failure.");

        System.out.println("type: " + error.getClass().getName());
        System.out.println("message: " + error.getMessage());
        System.out.println("cause: " + error.getCause());
        System.out.println("first stack frame: " + error.getStackTrace()[0]);
    }

    private static void showErrorIsUnchecked() {
        System.out.println("\n=== 2. Error is unchecked ===");
        System.out.println("The next method can throw ConfigurationError without declaring throws.");

        try {
            startApplication();
        } catch (ConfigurationError error) {
            System.out.println("caught for demo only: " + error.getMessage());
            System.out.println("root cause: " + error.getCause());
        }
    }

    private static void showCatchIsPossibleButRare() {
        System.out.println("\n=== 3. Catching Error is possible, but usually a bad idea ===");

        try {
            simulateFrameworkBoundary();
        } catch (Error error) {
            System.out.println("framework boundary records the serious failure: " + error.getMessage());
            System.out.println("Then it should usually stop or rethrow, not continue as if nothing happened.");
        }
    }

    private static void startApplication() {
        FileNotFoundException cause = new FileNotFoundException("application.properties was not found");
        throw new ConfigurationError("Required startup configuration is missing.", cause);
    }

    private static void simulateFrameworkBoundary() {
        throw new AssertionError("An impossible state was reached.");
    }
}
