/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.database;

import com.bacic5i5j.framework.database.AbstractSessionContext;

/**
 * @(#)MySQLSessionContext.java 1.0 15/03/2014
 */
public class MySQLSessionContext extends AbstractSessionContext {
    private static final MySQLSessionContext msc = new MySQLSessionContext();

    private MySQLSessionContext() {
        init("test.cfg.xml");
    }

    public static MySQLSessionContext getMSC() {
        return msc;
    }
}
