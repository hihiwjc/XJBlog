package cn.hihiwjc.app.xjblog.com.utils;

import android.os.Debug;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2015/1/12 0012
 * <br/>Func:App entry
 */
public class HprofUtils {

    private final static String OOM_SUFFIX = ".hprof";

    public static void dumpHprof(String path) {
        try {
            String name = getDate() + OOM_SUFFIX;
            path = path + File.separator + name;
            File file = new File(path);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            Debug.dumpHprofData(path);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
    }

}
