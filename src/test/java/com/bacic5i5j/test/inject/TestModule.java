/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.test.inject;

import com.bacic5i5j.framework.database.SessionContext;
import com.bacic5i5j.test.database.MySQLSessionContext;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @(#)TestModule.java 1.0 15/03/2014
 */
public class TestModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    @MySQL
    public SessionContext provideMySQL() {
        return MySQLSessionContext.getMSC();
    }
}
