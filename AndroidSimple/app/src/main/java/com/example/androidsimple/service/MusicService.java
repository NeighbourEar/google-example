/**
 * 
 */
package com.example.androidsimple.service;

import com.example.androidsimple.R;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * @author gl
 *
 */
public class MusicService extends Service {

	private MusicBinder musicBinder;
	private MediaPlayer mediaPlayer;
	private MusicReceiver musicReceiver;

	public void onCreate() {
		musicBinder = new MusicBinder();
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mediaPlayer = MediaPlayer.create(this, R.raw.music);
		mediaPlayer.start();
		musicReceiver = new MusicReceiver(this);
		musicReceiver.register();
		setNotification();

		return super.onStartCommand(intent, flags, startId);

	}

	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return musicBinder;
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(musicReceiver);
		super.onDestroy();
	}

	private class MusicBinder extends Binder {

	}
	/**
	 * 设置service前台运行，顶部的通知
	 */
	private void setNotification() {
		Notification.Builder builder = new Notification.Builder(this);
		builder.setWhen(System.currentTimeMillis());// 通知发生的时间
		builder.setSmallIcon(R.drawable.a); // 状态栏的小图标
		builder.setTicker("正在播放音乐");// 状态栏的标题
		// 下拉列表里的大图标
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
		builder.setContentTitle("最后的婚礼");// 下拉列表里的标题
		builder.setContentText("一次就好，我带你去看天涯海角");// 下拉列表里的内容
		builder.setContentInfo("fubiaoti");// 下拉列表的副标题
		builder.setAutoCancel(true);// 是否可以清楚
		startForeground(1, builder.build());// service里面启用notification，表示前台业务
	}

}
