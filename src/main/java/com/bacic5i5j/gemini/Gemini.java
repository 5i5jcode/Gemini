/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini;

import com.bacic5i5j.gemini.inject.GeminiModule;
import com.bacic5i5j.gemini.logs.Logger;
import com.bacic5i5j.gemini.logs.LoggerFactory;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.io.File;
import java.util.List;

/**
 * 这是系统应用的root
 *
 * @(#)Gemini.java 1.0 26/02/2014
 */
public class Gemini {
    public static final Gemini instance = new Gemini();

    private Injector injector;

    private File currentFolder;
    private LoggerFactory loggerFactory;
    private Logger logger;

    private Gemini() {
    }

    public void init() {
        List<Module> modules = Lists.newArrayList();
        modules.add(new GeminiModule(this));
        this.injector = buildInjector(modules);

        this.loggerFactory = getInstance(LoggerFactory.class);
        this.logger = getLogger("Gemini");

        logger.info("initializing Gemini...");
        this.currentFolder = innerCurrentFolder();

        logger.info("webapp director is: " + currentFolder.getAbsolutePath());
    }

    public <T> T getInstance(Class<T> type) {
        return injector().getInstance(type);
    }

    public <T> T injectMemebers(T instance) {
        injector().injectMembers(instance);
        return instance;
    }

    public Injector injector() {
        return injector;
    }

    /**
     * 根据名字获得logger
     *
     * @param name 需要获得的logger名字
     * @return 对应名字的logger
     */
    public Logger getLogger(String name) {
        return loggerFactory.getLogger(name);
    }

    /**
     * 根据类获得logger
     *
     * @param clazz 类
     * @return logger
     */
    public Logger getLogger(Class<?> clazz) {
        return loggerFactory.getLogger(clazz);
    }

    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Classloader所在文件夹
     *
     * @return 启动文件夹 classloader
     */
    public File currentFolder() {
        return currentFolder;
    }

    /****************************************/
    /**            PRIVATE METHOD          **/
    /****************************************/
    private File innerCurrentFolder() {
        return new File(getClass().getResource("../").getPath()).getParentFile();
    }

    private Injector buildInjector(List<Module> modules) {
        return Guice.createInjector(modules);
    }
}
