package com.zhangcanfu.myjava.app.exception;

import com.zhangcanfu.myjava.app.exception.model.LegacyBusinessException;
import com.zhangcanfu.myjava.app.exception.model.OrderLoadException;

import java.io.IOException;

public class ThrowableStudyDemo {

    public static void main(String[] args) {
        showBasicThrowable();
        showCauseChain();
        showInitCause();
        showSuppressedException();
        showStackTraceSnapshot();
    }

    private static void showBasicThrowable() {
        System.out.println("=== 1. Basic Throwable data ===");
        Throwable throwable = new Throwable("A validation problem was found.");
        printThrowable("basic", throwable);
    }

    private static void showCauseChain() {
        System.out.println("\n=== 2. Throwable cause chain ===");
        try {
            loadOrderFromService();
        } catch (OrderLoadException exception) {
            printThrowable("wrapped", exception);
        }
    }

    private static void showInitCause() {
        System.out.println("\n=== 3. Bind a cause with initCause ===");
        LegacyBusinessException legacyException = new LegacyBusinessException("Legacy exception without a cause constructor.");
        legacyException.initCause(new IOException("Failed to read config.txt"));
        printThrowable("legacy", legacyException);
    }

    private static void showSuppressedException() {
        System.out.println("\n=== 4. Suppressed exception example ===");
        try {
            useResource();
        } catch (Exception exception) {
            printThrowable("suppressed", exception);
        }
    }

    private static void showStackTraceSnapshot() {
        System.out.println("\n=== 5. Stack trace snapshot ===");
        try {
            levelOne();
        } catch (IllegalStateException exception) {
            StackTraceElement[] stackTrace = exception.getStackTrace();
            System.out.println("Top 3 stack frames captured when the exception was created:");
            for (int index = 0; index < Math.min(3, stackTrace.length); index++) {
                System.out.println("  at " + stackTrace[index]);
            }
        }
    }

    private static void loadOrderFromService() throws OrderLoadException {
        try {
            loadOrderFromRepository();
        } catch (IOException exception) {
            throw new OrderLoadException("Order loading failed after wrapping a lower-level IOException.", exception);
        }
    }

    private static void loadOrderFromRepository() throws IOException {
        throw new IOException("Database connection timed out.");
    }

    private static void useResource() throws Exception {
        // DemoResource.close() throws an exception so suppressed exceptions are visible.
        try (DemoResource resource = new DemoResource("order-file")) {
            throw new IllegalStateException("The main processing flow failed.");
        }
    }

    private static void levelOne() {
        levelTwo();
    }

    private static void levelTwo() {
        levelThree();
    }

    private static void levelThree() {
        throw new IllegalStateException("The exception object was created in levelThree.");
    }

    private static void printThrowable(String label, Throwable throwable) {
        System.out.println("[" + label + "] type: " + throwable.getClass().getName());
        System.out.println("[" + label + "] message: " + throwable.getMessage());
        System.out.println("[" + label + "] cause: " + throwable.getCause());
        System.out.println("[" + label + "] suppressed count: " + throwable.getSuppressed().length);

        StackTraceElement[] stackTrace = throwable.getStackTrace();
        if (stackTrace.length > 0) {
            System.out.println("[" + label + "] first stack frame: " + stackTrace[0]);
        }
    }

    private static final class DemoResource implements AutoCloseable {

        private final String resourceName;

        private DemoResource(String resourceName) {
            this.resourceName = resourceName;
        }

        @Override
        public void close() throws Exception {
            throw new IOException("Failed to close resource " + resourceName);
        }
    }
}
