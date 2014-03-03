/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.inject;

import com.bacic5i5j.gemini.Gemini;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * @(#)GeminiModule.java 1.0 27/02/2014
 */
public class GeminiModule extends AbstractModule {

    private final Gemini gemini;

    public GeminiModule(Gemini gemini) {
        this.gemini = gemini;
    }

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("template.path")).
                toInstance(gemini.currentFolder() + "pages/");
        bind(String.class).annotatedWith(Names.named("log.path")).
                toInstance(gemini.currentFolder() + "logs/gemini.log");
    }

    @Provides
    @Singleton
    private Gemini provideGemini() {
        return this.gemini;
    }
}
