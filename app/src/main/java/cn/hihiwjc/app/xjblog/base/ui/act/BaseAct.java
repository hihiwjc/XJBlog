package cn.hihiwjc.app.xjblog.base.ui.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.hihiwjc.app.xjblog.base.ui.frag.BaseFrag;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/4/24 0024
 * <br/>Func:Base Activity
 */

public abstract class BaseAct extends AppCompatActivity {
    // 当有Fragment Attach到这个Activity的时候，就会保存
    private Map<String, WeakReference<BaseFrag>> fragRefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragRefs = new HashMap<>();

        super.onCreate(savedInstanceState);
    }

    /**
     * 记录fragment
     */
    public void addFragment(String tag, BaseFrag frag) {
        fragRefs.put(tag, new WeakReference<>(frag));
    }

    /**
     * 移除fragment
     */
    public void removeFragment(String tag) {
        fragRefs.remove(tag);
    }

    /**
     * 返回键点击事件
     */
    @Override
    public void onBackPressed() {
        Set<String> keys = fragRefs.keySet();
        for (String key : keys) {
            WeakReference<BaseFrag> fragRef = fragRefs.get(key);
            BaseFrag fragment = fragRef.get();
            if (fragment != null) {
                fragment.onBackPress();
            }
        }
        super.onBackPressed();
    }
}
