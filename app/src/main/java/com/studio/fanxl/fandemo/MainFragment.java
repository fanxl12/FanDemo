package com.studio.fanxl.fandemo;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.studio.fanxl.fandemo.adapter.RecyclerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanxl on 2015/6/30.
 */
@EFragment(R.layout.main_fragment)
public class MainFragment extends Fragment {

	@ViewById
	RecyclerView recycler_view;
	@ViewById
	SwipeRefreshLayout swipe_container;

	private RecyclerAdapter adapter;
	private List<String> datas;

	@AfterViews
	void init(){
		//设置动画颜色
		swipe_container.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

		//设置布局管理器 创建默认的线性LayoutManager
		recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
		//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
		recycler_view.setHasFixedSize(true);
		//设置Item增加、移除动画
		recycler_view.setItemAnimator(new DefaultItemAnimator());
		//添加分割线
		recycler_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));

		initData();

		adapter = new RecyclerAdapter(datas);
		recycler_view.setAdapter(adapter);
		adapter.setOnItemClickListener(new RecyclerAdapter.OnRecyclerViewItemClickListener() {
			@Override
			public void onItemClick(View view, String data) {
				if(view.getId()==R.id.more){
					Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
				}
			}
		});

		swipe_container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				loadData();
			}
		});
	}

	@Background
	void loadData(){
		try {
			Thread.sleep(6000);
		}catch (Exception e){
			e.printStackTrace();
		}
		setLoadData();
	}

	@UiThread
	void setLoadData(){
		int size = datas.size();

		for (int i=size; i < size+2; i++){
			datas.add("网络获取数据"+i);
		}
		adapter.notifyItemInserted(size);
		swipe_container.setRefreshing(false);
	}

	private void initData(){
		datas = new ArrayList<String>();
		for (int i=0; i < 60; i++){
			datas.add("测试内容"+i);
		}
	}


}
