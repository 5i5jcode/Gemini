/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.core;

import com.bacic5i5j.framework.GeminiContext;
import com.bacic5i5j.framework.Model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @(#)DefaultGeminiContext.java 1.0 14/03/2014
 */
public class DefaultGeminiContext implements GeminiContext {
    private final Model model;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;

    public DefaultGeminiContext(HttpServletRequest request, HttpServletResponse response, Model model,
                                ServletContext servletContext) {
        this.model = model;
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public HttpServletRequest getRequest() {
        return this.request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return this.response;
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }
}
