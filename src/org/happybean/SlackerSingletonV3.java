package org.happybean;

/**
 * @author wgt
 * @date 2018-07-24
 * @description 懒汉式 线程安全 volatile禁止指令重排序
 **/
public class SlackerSingletonV3 {
    //通过volatile关键字来确保安全
    private volatile static SlackerSingletonV3 singleton;

    private SlackerSingletonV3(){}

    public static SlackerSingletonV3 getInstance(){
        if(singleton == null){
            synchronized (SlackerSingletonV3.class){
                if(singleton == null){
                    singleton = new SlackerSingletonV3();
                }
            }
        }
        return singleton;
    }
}
