package com.example.androidsimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class ScanBankCardActivity extends ActionBarActivity {

	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_bank_card);

		textView = (TextView) findViewById(R.id.textView1);

	}

	public void onClick(View v) {
		Intent scanIntent = new Intent(this, CardIOActivity.class);
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false);
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
		scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, false);
		scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false);// 是否需要显示过期时间
		startActivityForResult(scanIntent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String resultStr;
		// 如果扫描成功
		if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
			CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
			resultStr = "Card Number: " + scanResult.getFormattedCardNumber();
		}
		// 取消了扫描
		else {
			resultStr = "Scan was canceled.";
		}
		textView.setText(resultStr);

	}

}
