package com.gcl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

public class SlideMenu extends HorizontalScrollView {

	private int mWidth;
	private int mMenuWidth;
	private boolean isShow;

	// 构造函数
	public SlideMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlideMenu(Context context) {
		this(context, null);
	}

	public SlideMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		ViewGroup container = (ViewGroup) getChildAt(0);
		ViewGroup menuView = (ViewGroup) container.getChildAt(0);
		ViewGroup contentView = (ViewGroup) container.getChildAt(1);

		DisplayMetrics outMetrics = new DisplayMetrics();
		getDisplay().getMetrics(outMetrics);
		mWidth = outMetrics.widthPixels;
		mMenuWidth = mWidth / 3 * 2;

		android.view.ViewGroup.LayoutParams menuParams = menuView
				.getLayoutParams();
		menuParams.width = mMenuWidth;

		android.view.ViewGroup.LayoutParams contentParams = contentView
				.getLayoutParams();
		contentParams.width = mWidth;

		// 滑动到内容区
		scrollTo(mMenuWidth, 0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (MotionEvent.ACTION_UP == ev.getAction()) {
			int scrollX = getScrollX();
			if (scrollX > mMenuWidth / 2)
				this.smoothScrollTo(mMenuWidth, 0);
			else
				this.smoothScrollTo(0, 0);
			return true;
		}

		if (MotionEvent.ACTION_MOVE == ev.getAction()) {
			if (getScrollY() > 10) {
				return true;
			}
		}
		return super.onTouchEvent(ev);
	}

	public void showMenu() {
		smoothScrollTo(0, 0);
		isShow = true;
	}

	public void hideMenu() {
		smoothScrollTo(mMenuWidth, 0);
		isShow = false;
	}

	public void togger() {
		if (isShow) {
			hideMenu();
		} else {
			showMenu();
		}
	}
}
