/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.gate;


import com.bacic5i5j.framework.Gemini;
import com.bacic5i5j.framework.GeminiContext;
import com.bacic5i5j.framework.Model;
import com.bacic5i5j.framework.core.ContextFactory;
import com.bacic5i5j.framework.logs.LoggerFactory;
import com.bacic5i5j.framework.view.ViewFactory;
import org.slf4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * 与jersey框架集成，托管request， response，ServletContext等对象
 *
 * @(#)AbstractGate.java 1.0 14/03/2014
 */
public class AbstractGate {
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;
    @Context
    private ServletContext servletContext;

    protected Gemini gemini = Gemini.instance;

    // 数据模型、视图、上下文环境的接口初始化，以及日志的初始化
    protected Model model;
    protected ViewFactory viewFactory;
    protected GeminiContext geminiContext;
    protected Logger logger;

    public AbstractGate() {
        init();
    }


    void init() {
        model = gemini.getInstance(Model.class);
        viewFactory = gemini.getInstance(ViewFactory.class);
        geminiContext = gemini.getInstance(ContextFactory.class).
                create(servletContext, request, response, model);
        logger = gemini.getInstance(LoggerFactory.class).getLogger(this.getClass());
    }
}
