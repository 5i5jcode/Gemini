/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.database;

import com.bacic5i5j.bss.Gemini;
import com.bacic5i5j.test.inject.TestModule;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @(#)SessionContextTest.java 1.0 15/03/2014
 */
public class SessionContextTest {
    private Gemini gemini;

    @Before
    public void init() {
        gemini = Gemini.instance;
        gemini.addModule(new TestModule());
        gemini.init();
    }

    @Test
    public void testSessionContext() {
        TestAccess testAccess = gemini.getInstance(TestAccess.class);
        Assert.assertNotNull(testAccess);
    }

    @After
    public void end() {

    }
}
