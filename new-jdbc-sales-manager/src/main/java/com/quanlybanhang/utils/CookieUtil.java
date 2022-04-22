package com.quanlybanhang.utils;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class CookieUtil {
	private static CookieUtil cookieUtil;
	public static CookieUtil getInstance() {
		if(cookieUtil == null) {
			cookieUtil = new CookieUtil();
		}
		return cookieUtil;
	}
	
	public Cookie createCookie(String key, String value) {
		try {
			return new Cookie(key, URLEncoder.encode(value,"UTF-8"));
		} catch (Exception e) {
			return null;
		}
	}
	public Cookie[] getCookies(HttpServletRequest req,HttpServletResponse resp, StringBuilder sb, String key) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					String valueCookie = DeCodeUtil.getInstance().decode(cookie.getValue());
					sb.append(valueCookie);
				}
			}
		}
		return cookies;
	}
	public Cookie[] removeValue(HttpServletRequest req,HttpServletResponse resp, String key) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					cookie.setMaxAge(0);
				    cookie.setValue(null);
				    resp.addCookie(cookie);
				}
			}
		}
		return cookies;
	}
	
}
