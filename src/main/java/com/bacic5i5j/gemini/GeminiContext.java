/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理一个请求周期
 *
 * @(#)GeminiContext.java 1.0 27/02/2014
 */
public interface GeminiContext {
    /**
     * MVC 中的Model, 以key,value形式存放
     * @return
     */
    Model getModel();

    /**
     * 返回本次调用的 {@link HttpServletRequest}对象
     *
     * @return 当前请求
     */
    HttpServletRequest getRequest();

    /**
     * 返回本次调用的 {@link HttpServletResponse}对象
     *
     * @return 当前response
     */
    HttpServletResponse getResponse();

    /**
     * 得到ServletContext信息
     *
     * @return 当前ServletContext
     */
    ServletContext getServletContext();
}
