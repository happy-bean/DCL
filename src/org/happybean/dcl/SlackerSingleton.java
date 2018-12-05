package org.happybean.dcl;

/**
 * @author wgt
 * @date 2018-07-24
 * @description 懒汉式 线程不安全
 **/
public class SlackerSingleton {
    private static SlackerSingleton singleton;

    private SlackerSingleton() {
    }

    public static SlackerSingleton getInstance() {
        if (singleton == null) {
            singleton = new SlackerSingleton();
        }

        return singleton;
    }
}
