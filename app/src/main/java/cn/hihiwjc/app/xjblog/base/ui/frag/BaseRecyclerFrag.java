package cn.hihiwjc.app.xjblog.base.ui.frag;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import cn.hihiwjc.app.xjblog.R;
import cn.hihiwjc.app.xjblog.base.adapter.BaseRecyclerAdapter;
import cn.hihiwjc.app.xjblog.com.utils.SystemBarUtils;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/4/27 0027
 * <br/>Func:
 */

public abstract class BaseRecyclerFrag extends BaseFrag {
    @Bind(R.id.rv_main)
    RecyclerView mRvMain;
    protected BaseRecyclerAdapter mAdapter;

    @Override
    protected void initData() {
        mAdapter = bindListAdapter();
        mRvMain.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvMain.setAdapter(mAdapter);
        //修正虚拟导航栏的高度
        int fixPaddingBottom = SystemBarUtils.getNavigationBarHeight(getContext());
        mRvMain.setPadding(0, 0, 0, fixPaddingBottom);
    }

    @Override
    protected void initView() {
    }

    /**
     * 绑定列表适配器
     */
    protected abstract BaseRecyclerAdapter bindListAdapter();

    @Override
    protected int getLayoutID() {
        return R.layout.comm_recycler_list;
    }
}
