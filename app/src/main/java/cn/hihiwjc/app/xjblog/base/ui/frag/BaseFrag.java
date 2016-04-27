package cn.hihiwjc.app.xjblog.base.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/4/24 0024
 * <br/>Func:Base Fragment
 */

public abstract class BaseFrag extends Fragment {
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutID(), container, false);
        ButterKnife.bind(this, mRootView);
        initView();
        initData();
        return mRootView;
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 获取视图ID
     */
    protected abstract int getLayoutID();

    /**
     * 返回按键被点击了
     */
    public boolean onBackPress() {
        return false;
    }
}
