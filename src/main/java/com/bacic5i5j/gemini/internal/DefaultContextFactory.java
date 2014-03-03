/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.internal;

import com.bacic5i5j.gemini.GeminiContext;
import com.bacic5i5j.gemini.Model;
import com.bacic5i5j.gemini.gate.ContextFactory;
import com.google.inject.Singleton;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @(#)DefaultContextFactory.java 1.0 02/03/2014
 */
@Singleton
public class DefaultContextFactory implements ContextFactory {
    public DefaultContextFactory() {}

    @Override
    public GeminiContext create(ServletContext servletContext, HttpServletRequest request,
                                HttpServletResponse response, Model model) {
        return new DefaultGeminiContext(request, response, model, servletContext);
    }
}
