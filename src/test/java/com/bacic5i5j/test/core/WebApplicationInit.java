/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.core;

import com.bacic5i5j.framework.Gemini;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;

/**
 * @(#)WebApplicationInit.java 1.0 05/05/2014
 */
public class WebApplicationInit {
    private Gemini gemini = Gemini.instance;

    @Before
    public void init() {
        gemini.init();
    }

    public void testConfig() {
        PropertiesConfiguration config = gemini.getConfig();
        String webapp_dir = config.getString("webapp.dir");
        System.out.println(webapp_dir);
    }

    @Test
    public void testLog() {
        Logger logger = gemini.getLogger(WebApplicationInit.class);
        logger.info("test log.");
    }
}
