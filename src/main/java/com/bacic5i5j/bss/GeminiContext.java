/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这是会话环境的托管，负责维护一个请求线程内的数据模型，也可以将当前的请求，响应，以及ServletContext托管于此
 *
 * @(#)GeminiContext.java 1.0 13/03/2014
 */
public interface GeminiContext {
    /**
     * MVC 中的Model, 以key,value形式存放
     * @return
     */
    Model getModel();

    /**
     * 返回本次调用的 {@link javax.servlet.http.HttpServletRequest}对象
     *
     * @return 当前请求
     */
    HttpServletRequest getRequest();

    /**
     * 返回本次调用的 {@link javax.servlet.http.HttpServletResponse}对象
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
