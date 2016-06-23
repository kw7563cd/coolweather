package com.coolweather.app.util;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {
	
	/**
	 * parse and handle provinces which get from server
	 */
	public synchronized static boolean handleProvincesReponse(CoolWeatherDB coolWeatherDB, String response) {
		if (!TextUtils.isEmpty(response)) {
			try {
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				XmlPullParser xmlPullParser = factory.newPullParser();
				xmlPullParser.setInput(new StringReader(response));
				int eventType = xmlPullParser.getEventType();
				Province province = null;
				while (eventType != xmlPullParser.END_DOCUMENT) {
					String nodeName = xmlPullParser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if ("city".equals(nodeName)) {
							province = new Province();
							int count = xmlPullParser.getAttributeCount();
							if (count > 0) {
								province.setProvinceName(xmlPullParser.getAttributeValue(null, "quName"));
								province.setProvinceCode(xmlPullParser.getAttributeValue(null, "pyName"));
							}
						}
						break;
					case XmlPullParser.END_TAG:
						if (province != null) {
							coolWeatherDB.saveProvince(province);
						}
						break;
					default:
						break;
					}
					eventType = xmlPullParser.next();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * parse and handle cities which get from server
	 */
	public synchronized static boolean handleCitiesReponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			try {
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				XmlPullParser xmlPullParser = factory.newPullParser();
				xmlPullParser.setInput(new StringReader(response));
				int eventType = xmlPullParser.getEventType();
				City city = null;
				while (eventType != xmlPullParser.END_DOCUMENT) {
					String nodeName = xmlPullParser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if ("city".equals(nodeName)) {
							city = new City();
							int count = xmlPullParser.getAttributeCount();
							if (count > 0) {
								city.setCityName(xmlPullParser.getAttributeValue(null, "cityname"));
								city.setCityCode(xmlPullParser.getAttributeValue(null, "pyName"));
							}
						}
						break;
					case XmlPullParser.END_TAG:
						if (city != null) {
							city.setProvinceId(provinceId);
							coolWeatherDB.saveCity(city);
						}
						break;
					default:
						break;
					}
					eventType = xmlPullParser.next();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * parse and handle counties which get from server
	 */
	public synchronized static boolean handleCountiesReponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			try {
				XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
				XmlPullParser xmlPullParser = factory.newPullParser();
				xmlPullParser.setInput(new StringReader(response));
				int eventType = xmlPullParser.getEventType();
				County county = null;
				while (eventType != xmlPullParser.END_DOCUMENT) {
					String nodeName = xmlPullParser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if ("city".equals(nodeName)) {
							county = new County();
							int count = xmlPullParser.getAttributeCount();
							if (count > 0) {
								county.setCountyName(xmlPullParser.getAttributeValue(null, "cityname"));
								county.setCountyCode(xmlPullParser.getAttributeValue(null, "pyName"));
							}
						}
						break;
					case XmlPullParser.END_TAG:
						if (county != null) {
							county.setCityId(cityId);
							coolWeatherDB.saveCounty(county);
						}
						break;
					default:
						break;
					}
					eventType = xmlPullParser.next();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

}
