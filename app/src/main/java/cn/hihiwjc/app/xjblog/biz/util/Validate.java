package cn.hihiwjc.app.xjblog.biz.util;

import android.text.TextUtils;

import java.util.Map;

/**
 * @author Jan-Louis Crafford
 *         Created on 2016/01/08.
 */
public class Validate {

    public static boolean notNull(Object value) {
        return value != null;
    }

    public static void validateMapEntry(String key, Object value, Map<String, Object> map) {
        if (notNull(value)) {
            map.put(key, value);
        }
    }

    public static void validateMapStringEntry(String key, String value, Map<String, String> map) {
        if (!TextUtils.isEmpty(value)) {
            map.put(key, value);
        }
    }
}
