package com.data.info;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

public class MacAddress {

	private final static String TAG = "MacAddress";

	public static String getMacAddress(Context context) {
		Log.d(TAG, "getMacAddress");
		String macAddress = null;
		macAddress = getRawMacAddress(context);
		if (macAddress == null) {
			return null;
		}
		macAddress = macAddress.toUpperCase(Locale.US).trim();
		return "macAddress is " + macAddress;
	}

	public static String getRawMacAddress(Context context) {
		Log.d(TAG, "getRawMacAddress");
		final String wlanAddress = loadAddress("wlan");
		if (wlanAddress != null) {
			return wlanAddress;
		}

		WifiManager mWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		final String wifiAddress = mWifiManager.getConnectionInfo()
				.getMacAddress();
		return wifiAddress;
	}

	public static String loadAddress(String interfaceName) {
		Log.d(TAG, "loadAddress");
		final String filePath = "/sys/class/net/" + interfaceName + "/address";
		final StringBuilder fileData = new StringBuilder(1000);
		try {
			BufferedReader mBufferedReader = new BufferedReader(new FileReader(
					filePath), 1024);
			char[] buffer = new char[1024];
			int bufferNum;
			String bufferString;

			while ((bufferNum = mBufferedReader.read(buffer)) != -1) {
				bufferString = String.valueOf(buffer, 0, bufferNum);
				fileData.append(bufferString);
			}
			mBufferedReader.close();
			return fileData.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
