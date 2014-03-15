/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss;

import com.bacic5i5j.bss.inject.GeminiModule;
import com.bacic5i5j.bss.logs.LoggerFactory;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.slf4j.Logger;

import java.io.File;
import java.util.List;

/**
 * 这是应用的核心，上帝级的核心，里面包括了module的初始工作，日志的初始工作，模板的初始工作等等
 * 所有的工作都集中在这里
 *
 * @(#)Gemini.java 1.0 13/03/2014
 */
public class Gemini {
    public static final Gemini instance = new Gemini();

    private static final List<Module> modules = Lists.newArrayList();
    private Injector injector;

    private LoggerFactory loggerFactory;
    private Logger logger;

    private Gemini() {}

    /**
     * 用来进行Gemini的初始化工作
     */
    public void init() {
        modules.add(new GeminiModule(this));
        this.injector = Guice.createInjector(modules);

        this.loggerFactory = this.injector.getInstance(LoggerFactory.class);
        this.logger = loggerFactory.getLogger(Gemini.class);

        this.logger.info("核心初始化已完成！");

        logger.info("用户目录: " + currentFolder());
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public Logger getLogger(String name) {
        return loggerFactory.getLogger(name);
    }

    public Logger getLogger(Class type) {
        return loggerFactory.getLogger(type);
    }

    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }

    public File currentFolder() {
        return new File(getClass().getResource("/").getFile()).getParentFile().getParentFile();
    }
}
