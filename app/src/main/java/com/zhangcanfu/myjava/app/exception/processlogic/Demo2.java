package com.zhangcanfu.myjava.app.exception.processlogic;

/**
 * 抛出异常的代码是放在 try 外。
 *
 * 重点：
 * 1. print 方法把异常往 main 方法抛。
 * 2. print 方法中一旦 throw，throw 后面的循环代码不再执行。
 * 3. main 方法 catch 处理之后，catch 后面的“程序结束”继续执行。
 */
public class Demo2 {

    public static void main(String[] args) {
        try {
            print();
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("打印抛出异常");
        }

        System.out.println("程序结束");
    }

    private static void print() throws Exception {
        int index = 0;
        while (index < 15) {
            if (index == 5 || index == 10) {
                throw new Exception();
            }

            try {
                Thread.sleep(200);
                ++index;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("循环抛出异常");
            }

            System.out.println("index = " + index);
        }

        System.out.println("循环结束");
    }
}
