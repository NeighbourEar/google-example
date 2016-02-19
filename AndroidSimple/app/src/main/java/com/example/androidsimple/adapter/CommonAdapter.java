package com.example.androidsimple.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * @time 2015-6-11 下午3:36:57
 * 
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

	protected List<T> mLists;
	protected LayoutInflater mLayoutInflater;
	protected Context mContext;
	protected int mLayoutId;
	private ViewHolder viewHolder;

	/**
	 * 
	 * @param <T>
	 * @param context
	 *            context
	 * @param List
	 *            数据源
	 * @param layoutID
	 *            每个item的布局资源ID
	 */
	public CommonAdapter(Context context, List<T> lists, int layoutId) {
		this.mContext = context;
		this.mLists = lists;
		this.mLayoutId = layoutId;
		this.mLayoutInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mLists.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewHolder = ViewHolder.get(mContext, position, convertView, parent, mLayoutId);

		convert(viewHolder, getItem(position));

		return viewHolder.getmConvertView();
	}

	/**
	 * 子类必须实现此接口，用于处理每个item显示的类容
	 * 
	 * @param convertView
	 *            每个item的View
	 * @param viewHolder
	 *            每个item的view缓存
	 * @param position
	 *            当前item的行号
	 * @param data
	 *            每一行对应的实体类
	 */
	public abstract void convert(ViewHolder viewHolder, T data);

	/**
	 * @param listView
	 * @param headerView
	 */
	public void notifyDataSetChanged(ListView listView, View headerView) {
		notifyDataSetChanged();
		listView.removeHeaderView(headerView);
		if (getCount() == 0) {
			listView.addHeaderView(headerView);
		} else {
			listView.removeHeaderView(headerView);
		}
	}

}
