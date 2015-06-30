package com.studio.fanxl.fandemo;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.studio.fanxl.fandemo.adapter.TabAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by fanxl on 2015/6/30.
 */
@EActivity(R.layout.main_activity)
public class MainActivity extends AppCompatActivity {

    @ViewById
    Toolbar main_toolbar;
    @ViewById
    TabLayout main_tab;
    @ViewById
    ViewPager main_vp;
    @ViewById
    DrawerLayout drawer_layout;
    @ViewById
    NavigationView nav_view;
    @ViewById
    CoordinatorLayout main_content;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    void init(){
        setSupportActionBar(main_toolbar);

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(nav_view!=null){
            setupDrawerContent(nav_view);
        }

        if(main_vp!=null){
            setupViewPager(main_vp);
        }

        //tab和滑动界面绑定
        main_tab.setupWithViewPager(main_vp);
    }

    /**
     * 设置左滑菜单
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        //设置自动关闭
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                drawer_layout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * 设置可滑动界面
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new MainFragment_(), "Demo功能");
        adapter.addFragment(TestFragment.getInstance("消息"), "消息");
        adapter.addFragment(TestFragment.getInstance("联系人"), "联系人");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:

                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
