package org.happybean;

/**
 * @author wgt
 * @date 2018-07-24
 * @description 懒汉式 线程安全 synchronized导致性能下降
 **/
public class SlackerSingletonV1 {
    private static SlackerSingletonV1 singleton;

    private SlackerSingletonV1(){}

    public static synchronized SlackerSingletonV1 getInstance(){
        if(singleton == null){
            singleton = new SlackerSingletonV1();
        }

        return singleton;
    }
}
