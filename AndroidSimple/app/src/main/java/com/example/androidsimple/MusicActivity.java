package com.example.androidsimple;

import com.example.androidsimple.service.MusicReceiver;
import com.example.androidsimple.service.MusicService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class MusicActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		startService(new Intent(MusicActivity.this, MusicService.class));
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.start :
				Intent intent = new Intent(MusicReceiver.ACTION_NAME);
				intent.putExtra("name", "abc");
				sendBroadcast(intent);
				break;

			case R.id.stop :
				break;

			default :
				break;
		}
	}

}
