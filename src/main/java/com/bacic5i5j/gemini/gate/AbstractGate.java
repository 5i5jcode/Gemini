/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.gate;

import com.bacic5i5j.gemini.Gemini;
import com.bacic5i5j.gemini.logs.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * @(#)AbstractGate.java 1.0 02/03/2014
 */
public class AbstractGate {
    @Context
    HttpServletRequest request;
    @Context
    HttpServletResponse response;
    @Context
    ServletContext servletContext;

    ViewFactory viewFactory;
    ContextFactory contextFactory;

    Logger logger;

    final Gemini gemini = Gemini.instance;

    void init() {
        viewFactory = gemini.getInstance(ViewFactory.class);
        contextFactory = gemini.getInstance(ContextFactory.class);
    }
}
