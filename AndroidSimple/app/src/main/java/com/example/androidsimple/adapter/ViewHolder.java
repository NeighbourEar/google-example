package com.example.androidsimple.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author hwz
 * @time 2015-6-11 下午2:27:01
 * @tag ViewHolder
 * 
 */
public class ViewHolder {

	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;
	private Context mContext;

	public ViewHolder(Context context, int position, ViewGroup parent, int layoutId) {
		this.mPosition = position;
		this.mContext = context;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	public static ViewHolder get(Context context, int position, View convertView, ViewGroup parent,
			int layoutId) {
		if (convertView == null) {
			return new ViewHolder(context, position, parent, layoutId);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}
	}

	/**
	 * 通过viewId 获得view
	 * 
	 * @param viewId
	 * @return view
	 */
	public <T extends View> T getView(int viewId) {

		View mView = mViews.get(viewId);
		if (null == mView) {
			mView = mConvertView.findViewById(viewId);
			mViews.put(viewId, mView);
		}
		return (T) mView;
	}

	public void setBackground(int resId, int bgid) {
		getView(resId).setBackgroundResource(bgid);
	}

	public void setEditText(int resId, String text) {
		EditText textView = (EditText) getView(resId);
		textView.setText(text);
	}

	public void setText(int resId, String text) {
		TextView textView = (TextView) getView(resId);
		textView.setText(text);
	}

	public void setTextColor(int color, int resId) {
		TextView textView = (TextView) getView(resId);
		textView.setTextColor(color);
	}
	public void setTextBg(int resId, int bgid) {
		TextView textView = (TextView) getView(resId);
		textView.setBackgroundResource(bgid);
	}

	/**
	 * @param resourceId
	 *            资源id
	 * @param id
	 *            图片控件的id
	 */
	public void setImageResource(int resourceId, int id) {
		((ImageView) getView(id)).setImageResource(resourceId);
	}

	/**
	 * @param resId
	 * @param url
	 */
	public void setCompoundDrawables(int resId, Drawable drawable) {
		TextView textView = (TextView) getView(resId);
		textView.setCompoundDrawables(null, null, drawable, null);
	}

	public View getmConvertView() {
		return mConvertView;
	}

	public int getPosition() {
		return mPosition;
	}

}
