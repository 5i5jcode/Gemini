/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.gate;

import com.bacic5i5j.gemini.ActionResult;
import com.bacic5i5j.gemini.GeminiContext;
import com.bacic5i5j.gemini.Model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @(#)IndexGate.java 1.0 01/03/2014
 */
@Path("hello")
public class HelloGate extends AbstractGate {
    private Model model;

    public HelloGate() {
        model = gemini.getInstance(Model.class);
        init();
        logger = gemini.getLogger(HelloGate.class);
    }

    @GET
    @Produces
    public String getHello() {
        GeminiContext context = contextFactory.create(servletContext, request, response, model);
        ActionResult result = viewFactory.create("index");

        return result.render(context);
    }
}
