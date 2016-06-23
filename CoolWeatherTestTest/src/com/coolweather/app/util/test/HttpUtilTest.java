package com.coolweather.app.util.test;

import junit.framework.TestCase;

import org.junit.Test;

import android.util.Log;

import com.coolweather.app.util.HttpCallbackListener;
import com.coolweather.app.util.HttpUtil;

public class HttpUtilTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void testSendHttpRequest() {
		String address = "";
		address = "http://flash.weather.com.cn/wmaps/xml/china.xml";
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {

			@Override
			public void onFinish(String response) {
				System.out.println(response);
				Log.d("Test........", response);
			}

			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}
		});
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
