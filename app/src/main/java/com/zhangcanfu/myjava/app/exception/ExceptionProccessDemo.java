package com.zhangcanfu.myjava.app.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionProccessDemo {

    public static void main(String[] args) {
        showTryCatch();
        showMultipleCatch();
        showMultiCatch();
        showFinally();
        showThrow();
        showThrows();
        showCheckedException();
        showRuntimeException();
        showCustomException();
        showExceptionChain();
        showTryWithResources();
        showCatchOrder();
    }

    private static void showTryCatch() {
        System.out.println("=== 1. try-catch：捕获并处理异常 ===");

        try {
            int result = 10 / 0;
            System.out.println("result = " + result);
        } catch (ArithmeticException exception) {
            //ArithmeticException 算术异常 运行时异常
            // catch 会捕获 try 代码块中抛出的指定类型异常。
            System.out.println("捕获到除零异常：" + exception.getMessage());
        }
    }
    //mutiple 多重
    private static void showMultipleCatch() {
        System.out.println("\n=== 2. 多个 catch：不同异常，不同处理 ===");

        try {
            String[] names = {"Java", "Spring"};
            System.out.println(names[3]);
        } catch (NullPointerException exception) {
            System.out.println("对象是 null：" + exception.getMessage());
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("数组下标越界：" + exception.getMessage());
        }
    }

    private static void showMultiCatch() {
        System.out.println("\n=== 3. 一个 catch 捕获多种异常 ===");

        try {
            parseAndDivide("abc", 2);
        } catch (NumberFormatException | ArithmeticException exception) {
            // 多异常捕获适合“处理方式相同”的场景。
            System.out.println("数字处理失败：" + exception.getClass().getSimpleName());
        }
    }

    private static void showFinally() {
        System.out.println("\n=== 4. finally：无论是否异常，通常都会执行 ===");

        try {
            System.out.println("打开资源");
            int result = 10 / 2;
            System.out.println("正常计算结果：" + result);
        } catch (ArithmeticException exception) {
            System.out.println("计算失败：" + exception.getMessage());
        } finally {
            // finally 常用于释放资源，例如关闭文件、连接、锁等。
            System.out.println("关闭资源");
        }
    }

    private static void showThrow() {
        System.out.println("\n=== 5. throw：主动抛出异常对象 ===");

        try {
            checkAge(-1);
        } catch (IllegalArgumentException exception) {
            System.out.println("参数错误：" + exception.getMessage());
        }
    }

    private static void showThrows() {
        System.out.println("\n=== 6. throws：声明方法可能抛出的异常 ===");

        //如果选择不捕获，或者捕获后会继续抛出异常，可以方法签名加 throws语句 继续向上声明抛出
        try {
            readConfig();
        } catch (IOException exception) {
            System.out.println("调用方处理 IOException：" + exception.getMessage());
        }
    }

    private static void showCheckedException() {
        System.out.println("\n=== 7. checked exception：编译器强制处理 ===");

        try {
            loadFile("missing.txt");
        } catch (FileNotFoundException exception) {
            System.out.println("受检异常必须 catch 或 throws：" + exception.getMessage());
        }
    }

    private static void showRuntimeException() {
        System.out.println("\n=== 8. RuntimeException：不用 throws，也不用强制 catch ===");

        // validateQuantity 方法可能抛 RuntimeException，但这里不需要写 throws，也不需要 try-catch。
        validateQuantity(5);
        System.out.println("validateQuantity(5) 正常通过");

        try {
            validateQuantity(0);
        } catch (IllegalArgumentException exception) {
            // 这里 catch 只是为了让 demo 继续运行，不是编译器强制要求。
            System.out.println("运行时异常被捕获：" + exception.getMessage());
        }
    }

    private static void showCustomException() {
        System.out.println("\n=== 9. 自定义异常：表达业务含义 ===");

        try {
            submitOrder("");
        } catch (BusinessException exception) {
            System.out.println("业务异常：" + exception.getMessage());
        }
    }

    private static void showExceptionChain() {
        System.out.println("\n=== 10. 异常链：保留底层原因 cause ===");

        try {
            createReport();
        } catch (BusinessException exception) {
            System.out.println("外层异常：" + exception.getMessage());
            System.out.println("底层原因：" + exception.getCause());
        }
    }

    private static void showTryWithResources() {
        System.out.println("\n=== 11. try-with-resources：自动关闭资源 ===");

        try (DemoResource resource = new DemoResource("order.csv")) {
            resource.read();
            throw new IllegalStateException("主流程处理失败");
        } catch (Exception exception) {
            System.out.println("主异常：" + exception.getMessage());

            // close 方法里抛出的异常会被保存为 suppressed exception。
            for (Throwable suppressed : exception.getSuppressed()) {
                System.out.println("被抑制的异常：" + suppressed.getMessage());
            }
        }
    }

    private static void showCatchOrder() {
        System.out.println("\n=== 12. catch 顺序：先子类，后父类 ===");

        try {
            loadFileByIoApi("missing.txt");
        } catch (FileNotFoundException exception) {
            System.out.println("先捕获更具体的 FileNotFoundException");
        } catch (IOException exception) {
            System.out.println("再捕获更宽泛的 IOException");
        }

        // 不能把 IOException 写在 FileNotFoundException 前面，
        // 因为父类 catch 已经能接住子类异常，后面的子类 catch 会变成不可达代码。
    }

    private static void parseAndDivide(String numberText, int divisor) {
        int number = Integer.parseInt(numberText);
        System.out.println(number / divisor);
    }

    private static void checkAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("age 不能小于 0");
        }
    }

    private static void readConfig() throws IOException {
        throw new IOException("config.properties 读取失败");
    }

    private static void loadFile(String fileName) throws FileNotFoundException {
        throw new FileNotFoundException(fileName + " 不存在");
    }

    private static void loadFileByIoApi(String fileName) throws IOException {
        throw new FileNotFoundException(fileName + " 不存在");
    }

    private static void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity 必须大于 0，当前值：" + quantity);
        }
    }

    private static void submitOrder(String orderNo) throws BusinessException {
        if (orderNo == null || orderNo.trim().isEmpty()) {
            throw new BusinessException("订单号不能为空");
        }
    }

    private static void createReport() throws BusinessException {
        try {
            loadFile("report-template.xlsx");
        } catch (FileNotFoundException exception) {
            throw new BusinessException("创建报表失败", exception);
        }
    }

    private static final class BusinessException extends Exception {

        private BusinessException(String message) {
            super(message);
        }

        private BusinessException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    private static final class DemoResource implements AutoCloseable {

        private final String fileName;

        private DemoResource(String fileName) {
            this.fileName = fileName;
        }

        private void read() {
            System.out.println("读取资源：" + fileName);
        }

        @Override
        public void close() throws IOException {
            throw new IOException("关闭资源失败：" + fileName);
        }
    }
}
