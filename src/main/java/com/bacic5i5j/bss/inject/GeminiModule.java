/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.inject;

import com.bacic5i5j.bss.Gemini;
import com.bacic5i5j.bss.Model;
import com.bacic5i5j.bss.core.ContextFactory;
import com.bacic5i5j.bss.core.DefaultContextFactory;
import com.bacic5i5j.bss.core.DefaultModel;
import com.bacic5i5j.bss.logs.DefaultLoggerConfigue;
import com.bacic5i5j.bss.logs.DefaultLoggerFactory;
import com.bacic5i5j.bss.logs.LoggerConfigure;
import com.bacic5i5j.bss.logs.LoggerFactory;
import com.bacic5i5j.bss.view.VelocityViewFactory;
import com.bacic5i5j.bss.view.ViewFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * @(#)GeminiModule.java 1.0 13/03/2014
 */
public class GeminiModule extends AbstractModule {
    private final Gemini gemini;

    public GeminiModule(Gemini gemini) {
        this.gemini = gemini;
    }

    @Override
    protected void configure() {
        // 日志托管
        bind(LoggerConfigure.class).to(DefaultLoggerConfigue.class).in(Singleton.class);
        bind(LoggerFactory.class).to(DefaultLoggerFactory.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("log.path")).
                toInstance(gemini.currentFolder() + "/logs/boss.log");

        // 视图托管
        bind(ViewFactory.class).to(VelocityViewFactory.class).in(Singleton.class);
        bind(String.class).annotatedWith(Names.named("template.path")).
                toInstance(gemini.currentFolder() + "/WEB-INF/pages/");

        // 环境上下文
        bind(ContextFactory.class).to(DefaultContextFactory.class).in(Singleton.class);
        bind(Model.class).to(DefaultModel.class);
    }

    @Provides
    @Singleton
    public Gemini provideGemini() {
        return this.gemini;
    }
}
