package com.example.androidsimple;

import java.util.ArrayList;

import com.example.androidsimple.adapter.SimpleListAdapter;
import com.example.androidsimple.adapter.SimpleListBO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private ArrayList<SimpleListBO> list;
	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();

		setListener();
	}

	private void addList() {
		list.add(new SimpleListBO("线程示例", ThreadActivity.class));
		list.add(new SimpleListBO("自定义控件集合", CustomViewActivity.class));
		list.add(new SimpleListBO("扫描银行卡", ScanBankCardActivity.class));
		list.add(new SimpleListBO("播放音乐", MusicActivity.class));
	}

	private void init() {
		lv = (ListView) findViewById(R.id.listView);
		list = new ArrayList<>();
		addList();
		SimpleListAdapter adapter = new SimpleListAdapter(this, list);
		lv.setAdapter(adapter);
	}

	private void setListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity(new Intent(MainActivity.this, list.get(position).activity));
			}
		});
	}

	public void onClick(View v) {
		System.out.println(123);
	}

}
