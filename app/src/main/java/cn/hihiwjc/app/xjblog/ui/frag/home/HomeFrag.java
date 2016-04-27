package cn.hihiwjc.app.xjblog.ui.frag.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import cn.hihiwjc.app.xjblog.R;
import cn.hihiwjc.app.xjblog.base.adapter.BaseRecyclerAdapter;
import cn.hihiwjc.app.xjblog.base.adapter.CommRecyclerViewHolder;
import cn.hihiwjc.app.xjblog.base.ui.frag.BaseRecyclerFrag;
import cn.hihiwjc.app.xjblog.biz.mod.Post;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/4/24 0024
 * <br/>Func:
 */

public class HomeFrag extends BaseRecyclerFrag {
    @Bind(R.id.rv_main)
    RecyclerView mRvMain;

/*    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comm_recycler_list, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRvMain.setLayoutManager(layoutManager);
        mRvMain.setHasFixedSize(true);
        int navigationBarHeight = SystemBarUtils.getNavigationBarHeight(getContext());
        mRvMain.setPadding(0, 0, 0, navigationBarHeight);

        mRvMain.setAdapter(adapter);
        return rootView;
    }*/

    @Override
    protected BaseRecyclerAdapter bindListAdapter() {
        ArrayList<Post> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(new Post());
        }
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), mRvMain);
        adapter.fillData(data);
        return adapter;
    }

    class RecyclerAdapter extends BaseRecyclerAdapter<Post> {

        public RecyclerAdapter(Context context, RecyclerView parentView) {
            super(context, parentView);
        }

        @Override
        protected int getItemLayoutID() {
            return R.layout.item_post;
        }

        @Override
        protected void fillItemViewData(Post mod, CommRecyclerViewHolder holder, int position) {
            holder.setViewText(R.id.tv_item_post_title, "Winuxs" + position);
        }

    }

}
