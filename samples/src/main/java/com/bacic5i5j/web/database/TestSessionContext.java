/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.web.database;

import com.bacic5i5j.bss.database.AbstractSessionContext;

/**
 * @(#)TestSessionContext.java 1.0 15/03/2014
 */
public class TestSessionContext extends AbstractSessionContext {
    private static final TestSessionContext tsc = new TestSessionContext();

    private TestSessionContext() {
        init("test.cfg.xml");
    }

    public static TestSessionContext getTSC() {
        return tsc;
    }
}
