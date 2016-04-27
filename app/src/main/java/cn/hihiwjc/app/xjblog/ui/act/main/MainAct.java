package cn.hihiwjc.app.xjblog.ui.act.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.hihiwjc.app.xjblog.R;
import cn.hihiwjc.app.xjblog.base.ui.act.BaseAct;
import cn.hihiwjc.app.xjblog.ui.frag.home.HomeFrag;

/**
 * <br/>Author:hihiwjc
 * <br/>Email:hihiwjc@live.com
 * <br/>Date:2016/3/12 0012
 * <br/>Func:Home Activity
 */
public class MainAct extends BaseAct
        implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;
    @Bind(R.id.content_container)
    FrameLayout mContentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        mToolbar.setTitle(R.string.newest);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, new HomeFrag());
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 导航菜单事件
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switchFragments(id);
        return true;
    }

    /**
     * 切换Fragemnt
     */
    private void switchFragments(int menuId) {
        invalidateOptionsMenu();
        switch (menuId) {
            case R.id.nav_home:
                mToolbar.setTitle(R.string.newest);
                break;
            case R.id.nav_cat_dev:
                mToolbar.setTitle(R.string.cat_dev);
                break;
            case R.id.nav_cat_welfare:
                mToolbar.setTitle(R.string.cat_welfare);
                break;
            case R.id.nav_cat_acg:
                mToolbar.setTitle(R.string.cat_acg);
                break;
            case R.id.nav_cat_gay:
                mToolbar.setTitle(R.string.cat_gay);
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_about_us:
                break;
        }
        mDrawer.closeDrawer(GravityCompat.START);
    }
}
