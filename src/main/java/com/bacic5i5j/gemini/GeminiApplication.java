/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @(#)GeminiApplication.java 1.0 26/02/2014
 */
@ApplicationPath("resouces")
public class GeminiApplication extends ResourceConfig {
    public GeminiApplication() {
        Gemini.instance.init();
        packages("com.bacic5i5j.gemini.gate");
    }
}
