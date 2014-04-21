/**
 * Copyright (c) 2011, yoomai.net. All rights reserved.
 * yoomai.cn. Use is subject to license terms.
 */
package com.bacic5i5j.framework.toolbox.web;


import com.bacic5i5j.framework.Gemini;
import com.bacic5i5j.framework.toolbox.crypto.BASE64Coding;
import com.bacic5i5j.framework.toolbox.crypto.DESCoding;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @(#)WebUtils.java 1.0 04/12/2012
 * <p/>
 * 提供web工具
 * request参数管理、cookie管理、远程地址内容的获取，模拟post、get进行登录，表单提交等等
 * <p/>
 * 这个模块信息量巨大!
 */
public class WebUtils {
	private static Logger log = Gemini.instance.getLogger(WebUtils.class);

	/**
	 * 获得普通字符串型的参数
	 *
	 * @param request
	 * @param name
	 * @param def
	 * @return
	 */
	public static String getStringParameter(HttpServletRequest request, String name, String def) {
		if (request == null) {
			return def == null ? null : def;
		}

		String value = request.getParameter(name);
		if (value == null || "".equals(value)) {
			return def == null ? null : def;
		}

		return value;
	}

	/**
	 * 获得int类型的请求参数
	 *
	 * @param request
	 * @param name
	 * @param def
	 * @return
	 */
	public static int getIntParameter(HttpServletRequest request, String name, int def) {
		String value = getStringParameter(request, name, String.valueOf(def));
		return Integer.parseInt(value);
	}

	/**
	 * 获得long类型的请求参数
	 *
	 * @param request
	 * @param name
	 * @param def
	 * @return
	 */
	public static long getLongParameter(HttpServletRequest request, String name, long def) {
		String value = getStringParameter(request, name, String.valueOf(def));
		return Long.parseLong(value);
	}

	/**
	 * 获得double类型的请求参数
	 *
	 * @param request
	 * @param name
	 * @param def
	 * @return
	 */
	public static double getDoubleParameter(HttpServletRequest request, String name, double def) {
		String value = getStringParameter(request, name, String.valueOf(def));
		return Double.parseDouble(value);
	}

	/**
	 * 将请求地址所带参数转换成map进行返回
	 *
	 * @param request
	 * @return
	 */
	public static Map convertParameterToMap(HttpServletRequest request) {
		Map params = new HashMap();
		Enumeration enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String paramName = (String) enumeration.nextElement();
			String paramValue = WebUtils.getStringParameter(request, paramName, "");
			params.put(paramName, paramValue);
		}

		return params;
	}

	/**
	 * 设置cookie信息
	 *
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 * @param expiry
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, String domain, int expiry) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(domain);
		cookie.setPath("/");
		cookie.setMaxAge(expiry);

		response.addCookie(cookie);
	}

	/**
	 * 生成验证码
	 *
	 * @param code
	 * @param keys
	 * @return
	 */
	public static String generateCode(String code, byte[] keys) {
		DESCoding des = null;
		try {
			des = new DESCoding(keys);
		} catch (Exception e) {
			log.error("gernate verify code occured error: " + e.getMessage());
		}

		assert des != null;
		byte[] bs = des.encode(code.getBytes());
		return BASE64Coding.encode(bs);
	}

	/**
	 * 通过解码获得真实的验证码数字
	 *
	 * @param tvc
	 * @param keys
	 * @return
	 */
	public static String getCode(String tvc, byte[] keys) {
		String code = "";
		byte[] bs = BASE64Coding.decode(tvc);
		DESCoding des = null;
		if (bs != null) {
			try {
				des = new DESCoding(keys);
			} catch (Exception e) {
				log.error("decode verify code occured error: " + e.getMessage());
			}
			assert des != null;
			byte[] bs2 = des.decode(bs);
			if (bs2 != null) {
				code = new String(bs2);
			}
		}

		return code;
	}

    /**
     * 将对象转换为json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;

        try {
            jsonString = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("method toJson occured error: " + e.getMessage());
        }

        return jsonString;
    }
}
