package com.liuhe.demoipc;

/**
 * 使用Singleton帮助器类实现单例
 */
public class CommonUtil {

    private CommonUtil() {}

    private CommonUtil getInstance(){
        return instance.get();
    }

    private static final Singleton<CommonUtil> instance = new Singleton<CommonUtil>() {
        @Override
        protected CommonUtil create() {
            return new CommonUtil();
        }
    };
}
