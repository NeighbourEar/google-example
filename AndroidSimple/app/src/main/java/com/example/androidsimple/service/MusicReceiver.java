package com.example.androidsimple.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class MusicReceiver extends BroadcastReceiver {

	public static final String ACTION_NAME = "musicBroadcastReceiver";

	private Context context;

	/**
	 * @param context
	 */
	public MusicReceiver(Context context) {
		this.context = context;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("receive");
		System.out.println(intent.getStringExtra("name"));
	}

	public void register() {
		IntentFilter intentFilter = new IntentFilter(ACTION_NAME);
		context.registerReceiver(this, intentFilter);
	}

}