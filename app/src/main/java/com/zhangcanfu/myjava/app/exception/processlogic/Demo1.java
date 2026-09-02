package com.zhangcanfu.myjava.app.exception.processlogic;

/**
 * 抛出异常的代码是放在 try 中。
 *
 * 重点：
 * 1. print 方法内部自己 catch 异常，没有继续往 main 方法抛。
 * 2. 异常被 catch 处理之后，循环后面的代码继续执行。
 * 3. print 方法正常结束后，main 方法里 print 后面的代码也继续执行。
 */
public class Demo1 {

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

    private static void print() {
        int index = 0;
        while (index < 15) {
            try {
                Thread.sleep(200);
                ++index;
                if (index == 5 || index == 10) {
                    throw new Exception();
//                    System.out.println("抛出异常后代码");  //这行代码不会执行
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("循环抛出异常");
            }

            System.out.println("index = " + index);
        }

        System.out.println("循环结束");
    }
}
