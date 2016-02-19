package com.example.androidsimple.adapter;

import java.util.List;

import com.example.androidsimple.R;

import android.content.Context;

public class SimpleListAdapter extends CommonAdapter<SimpleListBO> {

	public SimpleListAdapter(Context context, List<SimpleListBO> lists) {
		super(context, lists, R.layout.simple_list_item);
	}

	public void convert(ViewHolder viewHolder, SimpleListBO data) {
		viewHolder.setText(R.id.tv, data.name);
	}

}