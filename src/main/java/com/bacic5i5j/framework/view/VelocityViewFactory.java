/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.view;

import com.bacic5i5j.framework.Gemini;
import com.bacic5i5j.framework.GeminiContext;
import com.google.inject.Inject;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolManager;
import org.apache.velocity.tools.config.EasyFactoryConfiguration;
import org.apache.velocity.tools.generic.DateTool;
import org.apache.velocity.tools.generic.NumberTool;
import org.slf4j.Logger;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @(#)DefaultViewFactory.java 1.0 13/03/2014
 */
public class VelocityViewFactory implements ViewFactory {
    private final static Logger logger = Gemini.instance.getLogger(VelocityViewFactory.class);

    private final VelocityEngine velocityEngine;
    private final String suffix = ".vm";
    private final ToolManager toolManager = new ToolManager();

    @Inject
    public VelocityViewFactory() {
        PropertiesConfiguration _config = Gemini.instance.getConfig();
        String templatePath = "/WEB-INF/pages";
        if (_config != null) {
            String _template_path = _config.getString("webapp.template");
            if (_template_path != null && !"".equals(_template_path.trim())) {
                templatePath = _template_path;
            }
        }

        Properties ps = new Properties();
        ps.setProperty("resource.loader", "file");
        ps.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        ps.setProperty("file.resource.loader.path", templatePath);
        ps.setProperty("file.resource.loader.cache", "false");
        ps.setProperty("file.resource.loader.modificationCheckInterval", "2");
        ps.setProperty("input.encoding", "UTF-8");
        ps.setProperty("output.encoding", "UTF-8");
        ps.setProperty("default.contentType", "text/html; charset=UTF-8");
        ps.setProperty("velocimarco.library.autoreload", "true");
        ps.setProperty("runtime.log.error.stacktrace", "false");
        ps.setProperty("runtime.log.warn.stacktrace", "false");
        ps.setProperty("runtime.log.info.stacktrace", "false");
        ps.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SimpleLog4JLogSystem");
        ps.setProperty("runtime.log.logsystem.log4j.category", "velocity_log");

        velocityEngine = new VelocityEngine();

        try {
            velocityEngine.init(ps);
            EasyFactoryConfiguration config = new EasyFactoryConfiguration();
            config.toolbox("application").tool("number", NumberTool.class).tool("date", DateTool.class);
            toolManager.configure(config);
            toolManager.setVelocityEngine(velocityEngine);
        } catch (Exception e) {
            logger.error(e.getMessage() + " : " + e.getCause());
        }
    }

    @Override
    public String render(GeminiContext context, String viewName) {
        String result = "";
        Template template = velocityEngine.getTemplate(viewName + suffix);

        //init context
        //Context ctx = new VelocityContext(context.getModel().getModel());
        Context ctx = toolManager.createContext();
        Map maps = context.getModel().getModel();
        Iterator keys = maps.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object object = maps.get(key);
            ctx.put(key, object);
        }

        // render
        StringWriter writer = new StringWriter();
        template.merge(ctx, writer);
        result = writer.toString();

        return result;
    }
}
