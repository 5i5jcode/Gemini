/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.web.gate;

import com.bacic5i5j.framework.Gemini;
import com.bacic5i5j.framework.gate.AbstractGate;
import com.bacic5i5j.web.database.TestAccess;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @(#)IndexGate.java 1.0 15/03/2014
 */
@Path("index")
public class IndexGate extends AbstractGate {
    private TestAccess testAccess;

    public IndexGate() {
        super();

        logger.info("IndexGate init ok.");
        testAccess = Gemini.instance.getInstance(TestAccess.class);
    }

    @GET
    @Produces
    public String index() {
        List list = testAccess.all();
        return "index: " + list.size();
    }
}
