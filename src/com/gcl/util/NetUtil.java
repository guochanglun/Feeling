package com.gcl.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 检测网络情况
 * 
 */
public class NetUtil {

	public static boolean isConn(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
		for (NetworkInfo info : networkInfo) {
			if (info.getState() == NetworkInfo.State.CONNECTED) {
				return true;
			}
		}
		return false;
	}
}
