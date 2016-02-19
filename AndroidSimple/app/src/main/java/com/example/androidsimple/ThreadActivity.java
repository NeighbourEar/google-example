package com.example.androidsimple;

import com.example.androidsimple.utils.DensityUtils;
import com.example.androidsimple.utils.MyUtils;
import com.example.androidsimple.view.SectorView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class ThreadActivity extends ActionBarActivity implements OnClickListener {

	public int TICKETS_NUMBER = 90;

	private int totalTickets;
	private Context context;
	private Salehandler mHandler;
	private TextView tv1, tv2, tv3;
	private View view1, view2, view3;
	private int height;// 售票的高度
	private SectorView sectorView;
	private boolean isSaling = false;
	private Button bt_start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thread);

		init();

	}

	private void init() {
		tv1 = (TextView) findViewById(R.id.tv1);
		tv2 = (TextView) findViewById(R.id.tv2);
		tv3 = (TextView) findViewById(R.id.tv3);
		view1 = findViewById(R.id.view1);
		view2 = findViewById(R.id.view2);
		view3 = findViewById(R.id.view3);
		bt_start = (Button) findViewById(R.id.bt_start);
		sectorView = (SectorView) findViewById(R.id.sectorView);

		context = this;
		height = DensityUtils.dp2px(context, 350);
		mHandler = new Salehandler();
		bt_start.setOnClickListener(this);
	}

	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.bt_start :
				if (isSaling) {
					MyUtils.showToast(context, "正在售票中...", 1);
					return;
				}
				isSaling = true;
				totalTickets = TICKETS_NUMBER;
				setTextAndHeight(tv1, view1, 0);
				setTextAndHeight(tv2, view2, 0);
				setTextAndHeight(tv3, view3, 0);
				System.out.println("start");
				for (int i = 0; i < 3; i++) {
					new Thread(new SaleTickets(i)).start();
				}
				break;

			default :
				break;
		}

	}

	private class Salehandler extends Handler {
		public void handleMessage(Message msg) {
			sectorView.setPercent(totalTickets / (float) TICKETS_NUMBER);
			switch (msg.what) {
				case 0 :
					setTextAndHeight(tv1, view1, msg.arg1);
					break;
				case 1 :
					setTextAndHeight(tv2, view2, msg.arg1);
					break;
				case 2 :
					setTextAndHeight(tv3, view3, msg.arg1);
					break;
				case 10 :
					MyUtils.showToast(context, "售票已结束", 1);
					isSaling = false;

					break;
				default :
					break;
			}
		}
	}

	public class SaleTickets implements Runnable {

		private int type;
		private int hasSaled = 0;

		/**
		 * @param type
		 */
		public SaleTickets(int type) {
			this.type = type;
		}

		public void run() {
			while (totalTickets > 0) {
				// 模拟耗时操作
				try {
					Thread.sleep((long) (Math.random() * 300));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (context) {
					if (totalTickets > 0) {
						totalTickets--;
						hasSaled++;

						Message message = new Message();
						message.what = type;
						message.arg1 = hasSaled;
						mHandler.sendMessage(message);
					}
				}
			}
			mHandler.sendEmptyMessage(10);
		}

	}

	public void setTextAndHeight(TextView tv, View view, float number) {
		tv.setText("已售出：" + (int) number + "张");
		LayoutParams lp = (LayoutParams) view.getLayoutParams();
		lp.height = (int) (number / TICKETS_NUMBER * height);
		view.setLayoutParams(lp);

	}

}
