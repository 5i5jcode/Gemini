/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.gate;

import com.bacic5i5j.gemini.Gemini;
import com.bacic5i5j.gemini.GeminiContext;
import com.bacic5i5j.gemini.Model;
import com.bacic5i5j.gemini.logs.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * 所有http房源资源的入口抽象类，该类提供了每个线程访问时的request, response和ServletContext对象。
 * 除此之外，该访问资源抽象类还提供了用于生成模板的ViewFactory实例以及当前请求的上下文环境生成器。
 *
 * 每个请求访问资源都可以访问到Gemini对象，该对象是上帝！
 * 当继承的请求资源需要访问请求上下文环境以及ViewFactory，需要先行调用init接口，参数model是用于模板显示
 * 的值存储对象。
 *
 * @(#)AbstractGate.java 1.0 02/03/2014
 */
public class AbstractGate {
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;
    @Context
    private ServletContext servletContext;

    protected ViewFactory viewFactory;
    protected GeminiContext geminiContext;

    protected Logger logger;

    protected final Gemini gemini = Gemini.instance;

    protected void init(Model model) {
        viewFactory = gemini.getInstance(ViewFactory.class);
        ContextFactory contextFactory = gemini.getInstance(ContextFactory.class);
        geminiContext = contextFactory.create(servletContext, request, response, model);
    }
}
