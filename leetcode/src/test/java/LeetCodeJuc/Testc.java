package LeetCodeJuc;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import java.util.function.IntConsumer;

import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Testc {

    /**
     * 1116. 打印零与奇偶数
     * <p>
     * 相同的一个ZeroEvenOdd类实例将会传递给三个不同的线程：
     * <p>
     * 线程 A 将调用zero()，它只输出 0 。
     * 线程 B 将调用even()，它只输出偶数。
     * 线程 C 将调用odd()，它只输出奇数。
     * 每个线程都有一个printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列010203040506... ，其中序列的长度必须为 2n。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/print-zero-even-odd
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    class ZeroEvenOdd {
        private int n;
        private volatile int current = 0;
        private final Object zero = new Object();


        public ZeroEvenOdd(int n) {
            System.out.println("n ==" + n);
            this.n = n;
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            synchronized (zero) {
                while (true) {
                    if (current > 0) {
                        zero.notifyAll();
                        zero.wait();
                    }
                    current = -current + 1;
                    if (current > n) {
                        break;
                    }
                    System.out.println(0);
                    printNumber.accept(0);

                    System.out.println("zero 释放");
                    zero.notifyAll();
                    zero.wait();
                }
            }

        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            System.out.println("even current== " + current);


            synchronized (zero) {
                while (current == 0 || current % 2 == 1 || current > n) {
                    System.out.println("even wait " + current);
                    zero.wait();
                }
                System.out.println("even enter");
                System.out.println(current);
                printNumber.accept(current);
                current = -current;
                zero.notifyAll();
                System.out.println("even 释放");
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            System.out.println("odd current== " + current);


            synchronized (zero) {

                while (current == 0 || current % 2 == 0 || current > n) {
                    System.out.println("odd wait " + current);
                    zero.wait();
                }
                System.out.println("odd enter");
                System.out.println(current);
                printNumber.accept(current);
                current = -current;
                zero.notifyAll();
                System.out.println("odd 释放");
            }

        }
    }

    @Test
    public void test() {
        final FooBar fooBar = new FooBar(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.foo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("foo");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.bar(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("bar");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class FooBar {
        private int n;
        private volatile boolean first = true;
        private final Object object = new Object();

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {
            synchronized (object) {
                for (int i = 0; i < n; i++) {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    if (!first) {
                        object.wait();

                    }
                    printFoo.run();
                    first = false;
                    object.notify();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            synchronized (object) {
                for (int i = 0; i < n; i++) {

                    // printBar.run() outputs "bar". Do not change or remove this line.

                    if (first) {
                        object.wait();
                    }
                    printBar.run();
                    first = true;
                    object.notify();
                }
            }
        }
    }


    /**
     * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
     * <p>
     * 如果这个数字可以被 3 整除，输出 "fizz"。
     * 如果这个数字可以被 5 整除，输出"buzz"。
     * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
     * 例如，当n = 15，输出：1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
     * <p>
     * 假设有这么一个类：
     * <p>
     * class FizzBuzz {
     * public FizzBuzz(int n) { ... }              // constructor
     * public void fizz(printFizz) { ... }          // only output "fizz"
     * public void buzz(printBuzz) { ... }          // only output "buzz"
     * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
     * public void number(printNumber) { ... }      // only output the numbers
     * }
     * 请你实现一个有四个线程的多线程版FizzBuzz，同一个FizzBuzz实例会被如下四个线程使用：
     * <p>
     * 线程A将调用fizz()来判断是否能被 3 整除，如果可以，则输出fizz。
     * 线程B将调用buzz()来判断是否能被 5 整除，如果可以，则输出buzz。
     * 线程C将调用fizzbuzz()来判断是否同时能被 3 和 5 整除，如果可以，则输出fizzbuzz。
     * 线程D将调用number()来实现输出既不能被 3 整除也不能被 5 整除的数字。
     * <p>
     * <p>
     * 提示：
     * <p>
     * 本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fizz-buzz-multithreaded
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    class FizzBuzz {
        private int n;


        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            printFizz.run();

        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {

        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {

        }

//        public int getStatus(int n) {
//
//        }


    }
}
