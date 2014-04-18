/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.web;

import com.bacic5i5j.framework.Gemini;
import com.bacic5i5j.web.inject.SampleModule;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @(#)WebApplication.java 1.0 15/03/2014
 */
public class WebApplication extends ResourceConfig {
    public WebApplication() {
        Gemini.instance.addModule(new SampleModule());
        Gemini.instance.init();
        packages("com.bacic5i5j.web.gate");
    }
}
