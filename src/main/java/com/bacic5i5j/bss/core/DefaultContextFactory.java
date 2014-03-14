/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.core;

import com.bacic5i5j.bss.GeminiContext;
import com.bacic5i5j.bss.Model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @(#)DefaultContextFactory.java 1.0 14/03/2014
 */
public class DefaultContextFactory implements ContextFactory {
    @Override
    public GeminiContext create(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Model model) {
        return new DefaultGeminiContext(request, response, model, servletContext);
    }
}
