package com.studio.fanxl.fandemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studio.fanxl.fandemo.R;

import java.util.List;

/**
 * Created by fanxl on 2015/6/30.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

	private List<String> datas;

	public RecyclerAdapter(){}

	public RecyclerAdapter(List<String> datas){
		this.datas=datas;
		Log.i("adapter", "数据个数:"+datas.size());
	}

	private OnRecyclerViewItemClickListener mOnItemClickListener = null;

	@Override
	public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recyclerview_item, parent, false);
		ViewHolder vh = new ViewHolder(view);

		//将创建的View注册点击事件
		vh.content.setOnClickListener(new ItemClick());
		vh.more.setOnClickListener(new ItemClick());
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.mTextView.setText(datas.get(position));
		//将数据保存在itemView的Tag中，以便点击时进行获取
		holder.content.setTag(datas.get(position));
		holder.more.setTag("更多");
	}

	@Override
	public int getItemCount() {
		return datas.size();
	}

	/**
	 * 设置条目监听
	 *
	 * @param listener
	 */
	public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
		this.mOnItemClickListener = listener;
	}

	/**
	 * 条目点击事件接口
	 */
	public interface OnRecyclerViewItemClickListener {
		void onItemClick(View view, String data);
	}

	/**
	 * 菜单点击事件接口
	 */
	public interface OnRecyclerViewMenuClickListener {
		void onMenuClick(View view, int position);
	}

	class ItemClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			if(mOnItemClickListener!=null){
				mOnItemClickListener.onItemClick(v, v.getTag().toString());
			}
		}
	}

	/**
	 * 自定义的ViewHolder，持有每个Item的的所有界面元素
	 */
	public class ViewHolder extends RecyclerView.ViewHolder{

		private TextView mTextView;
		private LinearLayout content;
		private ImageButton more;

		public ViewHolder(View view) {
			super(view);
			mTextView = (TextView) view.findViewById(R.id.text);
			content = (LinearLayout) view.findViewById(R.id.content);
			more = (ImageButton) view.findViewById(R.id.more);
		}
	}
}
