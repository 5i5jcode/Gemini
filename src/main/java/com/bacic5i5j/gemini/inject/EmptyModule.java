/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.inject;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * @(#)EmptyModule.java 1.0 03/03/2014
 */
public class EmptyModule extends AbstractModule {
    protected static String TEMPLATE_PATH;
    protected static String LOG_PATH;

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("template.path")).
                toInstance(TEMPLATE_PATH);
        bind(String.class).annotatedWith(Names.named("log.path")).
                toInstance(LOG_PATH);
    }
}
