package com.springMVC.hello.utility;

import javax.servlet.http.HttpServletRequest;

public class JSPUtils {

	public static String getURL(String url,
			HttpServletRequest request) {
		
		String contextPath = request.getContextPath();
		String URL = contextPath + url;
		return URL;
	}

}
