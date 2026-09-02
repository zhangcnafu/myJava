package com.zhangcanfu.myjava.app.exception;

import com.zhangcanfu.myjava.app.exception.model.OrderLoadException;
import com.zhangcanfu.myjava.app.exception.model.OrderValidationException;

import java.io.IOException;

public class ExceptionRuntimeExceptionStudyDemo {

    public static void main(String[] args) {
        showCheckedExceptionMustBeHandled();
        showRuntimeExceptionIsUnchecked();
        showRuntimeExceptionIsStillException();
    }

    private static void showCheckedExceptionMustBeHandled() {
        System.out.println("=== 1. Exception can be checked ===");

        try {
            loadOrder("PO-404");
        } catch (OrderLoadException exception) {
            System.out.println("checked exception was handled: " + exception.getMessage());
            System.out.println("cause: " + exception.getCause());
        }
    }

    private static void showRuntimeExceptionIsUnchecked() {
        System.out.println("\n=== 2. RuntimeException is unchecked ===");

        validateQuantity(3);
        System.out.println("validateQuantity(3) compiled and ran without throws or catch.");

        try {
            validateQuantity(-3);
        } catch (OrderValidationException exception) {
            System.out.println("validateQuantity(-3) was caught only so this demo can continue: " + exception.getMessage());
        }
    }

    private static void showRuntimeExceptionIsStillException() {
        System.out.println("\n=== 3. RuntimeException is still an Exception ===");

        try {
            validateQuantity(0);
        } catch (Exception exception) {
            System.out.println("catch Exception also catches RuntimeException: " + exception.getClass().getSimpleName());
        }
    }

    private static void loadOrder(String orderNo) throws OrderLoadException {
        try {
            readOrderFile(orderNo);
        } catch (IOException exception) {
            throw new OrderLoadException("Cannot load order " + orderNo, exception);
        }
    }

    private static void readOrderFile(String orderNo) throws IOException {
        throw new IOException("Order file does not exist: " + orderNo + ".txt");
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new OrderValidationException("quantity must be greater than 0, actual: " + quantity);
        }
    }
}
