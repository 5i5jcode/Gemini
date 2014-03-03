/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.internal;

import com.bacic5i5j.gemini.GeminiContext;
import com.bacic5i5j.gemini.Model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @(#)DefaultGeminiContext.java 1.0 27/02/2014
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
        return model;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }
}
