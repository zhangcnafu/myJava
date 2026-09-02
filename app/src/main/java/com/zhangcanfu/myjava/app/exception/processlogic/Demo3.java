package com.zhangcanfu.myjava.app.exception.processlogic;

/**
 * 不对异常进行捕获，只是往上抛。
 *
 * 重点：
 * 1. print 方法把异常抛给 main 方法。
 * 2. main 方法继续往 JVM 抛，没有 catch 处理。
 * 3. print 后面的“程序结束”不会执行，程序直接异常终止。
 */
public class Demo3 {

    public static void main(String[] args) throws Exception {
        print();
        System.out.println("程序结束");
    }

    private static void print() throws Exception {
        int index = 0;
        while (index < 15) {
            ++index;
            if (index == 5 || index == 10) {
                throw new Exception();
            }

            System.out.println("index = " + index);
        }

        System.out.println("循环结束");
    }
}
