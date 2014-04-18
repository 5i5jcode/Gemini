/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.web.inject;

import com.bacic5i5j.framework.database.SessionContext;
import com.bacic5i5j.web.database.TestSessionContext;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @(#)SampleModule.java 1.0 15/03/2014
 */
public class SampleModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    @MySQLDB
    public SessionContext provideMySQL() {
        return TestSessionContext.getTSC();
    }
}
