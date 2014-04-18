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
 * @(#)ContextFactory.java 1.0 14/03/2014
 */
public interface ContextFactory {
    GeminiContext create(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response,
                         Model model);
}
