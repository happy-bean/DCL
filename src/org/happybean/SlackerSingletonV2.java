package org.happybean;

/**
 * @author wgt
 * @date 2018-07-24
 * @description 懒汉式 线程安全 double check lock 双重检查 看似完美
 **/
public class SlackerSingletonV2 {
    private static SlackerSingletonV2 singleton;

    private SlackerSingletonV2() {
    }

    public static SlackerSingletonV2 getInstance() {
        if (singleton == null) {                                      // 1
            synchronized (SlackerSingletonV2.class) {                 // 2
                if (singleton == null) {                              // 3
                    singleton = new SlackerSingletonV2();             // 4
                }
            }
        }
        return singleton;
    }
}

//就如上面所示，这个代码看起来很完美，理由如下：
//
//        如果检查第一个singleton不为null,则不需要执行下面的加锁动作，极大提高了程序的性能；
//        如果第一个singleton为null,即使有多个线程同一时间判断，但是由于synchronized的存在，只会有一个线程能够创建对象；
//        当第一个获取锁的线程创建完成后singleton对象后，其他的在第二次判断singleton一定不会为null，
//        则直接返回已经创建好的singleton对象；
//        通过上面的分析，DCL看起确实是非常完美，但是可以明确地告诉你，这个错误的。上面的逻辑确实是没有问题，
//        分析也对，但是就是有问题，那么问题出在哪里呢？
//        在回答这个问题之前，我们先来复习一下创建对象过程，实例化一个对象要分为三个步骤：
//
//        1.分配内存空间
//        2.初始化对象
//        3.将内存空间的地址赋值给对应的引用
//        但是由于重排序的缘故，步骤2、3可能会发生重排序，其过程如下：
//
//        1.分配内存空间
//        2.将内存空间的地址赋值给对应的引用
//        3.初始化对象
//        如果2、3发生了重排序就会导致第二个判断会出错，singleton != null，
//        但是它其实仅仅只是一个地址而已，此时对象还没有被初始化，所以return的singleton对象是一个没有被初始化的对象
