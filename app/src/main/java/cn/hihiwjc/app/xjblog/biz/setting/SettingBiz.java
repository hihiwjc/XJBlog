package cn.hihiwjc.app.xjblog.biz.setting;

import cn.hihiwjc.app.xjblog.com.setting.SettingUtility;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/3/21 0021
 * <br/>Func:Settings Logic
 */

public class SettingBiz {
    private static SettingBiz self = new SettingBiz();

    private SettingBiz() {
    }

    public void init() {
        SettingUtility.addSettings("settings");
    }

    public static SettingBiz getSelf() {
        return self;
    }

    /**
     * @return 加载服务器域名
     */
    public String loadServerIP() {
        return SettingUtility.getStringSetting("server_ip");
    }
}
