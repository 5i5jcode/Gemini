/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.inject;

import com.bacic5i5j.bss.Gemini;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @(#)GeminiModule.java 1.0 13/03/2014
 */
public class GeminiModule extends AbstractModule {
    private final Gemini gemini;

    public GeminiModule(Gemini gemini) {
        this.gemini = gemini;
    }

    @Override
    protected void configure() {}

    @Provides
    @Singleton
    public Gemini provideGemini() {
        return this.gemini;
    }
}
