/**
 * 
 */
package com.example.androidsimple.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author gl
 *
 */
public class MyUtils {
	// 自定义Toast，快速切换Toast
	private static Toast toast;

	public static void showToast(Context context, String msg, int duration) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, duration);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}
}
