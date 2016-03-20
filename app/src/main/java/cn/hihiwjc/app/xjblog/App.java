package cn.hihiwjc.app.xjblog;

import cn.hihiwjc.app.xjblog.base.BaseApp;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/3/12 0012
 * <br/>Func:App entry
 */

public class App extends BaseApp {
    private static App self;

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
    }

    public static App getSelf() {
        return self;
    }
}
