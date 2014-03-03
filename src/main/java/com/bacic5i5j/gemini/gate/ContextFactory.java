/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.gate;

import com.bacic5i5j.gemini.GeminiContext;
import com.bacic5i5j.gemini.Model;
import com.bacic5i5j.gemini.internal.DefaultContextFactory;
import com.google.inject.ImplementedBy;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @(#)ContextFactory.java 1.0 02/03/2014
 */
@ImplementedBy(DefaultContextFactory.class)
public interface ContextFactory {
    GeminiContext create(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response,
                         Model model);
}
