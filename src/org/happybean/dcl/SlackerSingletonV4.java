package org.happybean.dcl;

/**
 * @author wgt
 * @date 2018-07-24
 * @description 懒汉式 线程安全 类初始化的解决方案
 **/
public class SlackerSingletonV4 {
    private static class SingletonHolder {
        public static SlackerSingletonV4 singleton = new SlackerSingletonV4();
    }

    public static SlackerSingletonV4 getInstance() {
        return SingletonHolder.singleton;
    }
}