/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.internal;

import com.bacic5i5j.gemini.ActionResult;
import com.bacic5i5j.gemini.GeminiContext;
import com.bacic5i5j.gemini.exceptions.GeminiException;
import com.bacic5i5j.gemini.gate.ViewFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.RuntimeInstance;

import java.io.StringWriter;
import java.util.Properties;

/**
 * @(#)VelocityViewFactory.java 1.0 27/02/2014
 */
@Singleton
public class VelocityViewFactory implements ViewFactory {
    private final RuntimeInstance rtInstance;

    private final String suffix = ".vm";

    @Inject
    public VelocityViewFactory(@Named("template.path") String templatePath) {
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

        rtInstance = new RuntimeInstance();

        try {
            rtInstance.init(ps);
        } catch (Exception e) {
            throw GeminiException.raise(e);
        }
    }

    Template getTemplate(String viewName) {
        return rtInstance.getTemplate(viewName + suffix);
    }

    @Override
    public ActionResult create(String viewName) {
        return new VelocityViewResult(this, viewName);
    }

    public static class VelocityViewResult implements ActionResult {
        private final VelocityViewFactory factory;

        private final String viewName;

        private VelocityViewResult(VelocityViewFactory factory, String viewName) {
            this.factory = factory;
            this.viewName = viewName;
        }

        @Override
        public String render(GeminiContext geminiContext) {

            String result;

            Template template = factory.getTemplate(viewName);
            // init context:
            Context context = new VelocityContext(geminiContext.getModel().getModel());
            // render:
            StringWriter writer = new StringWriter();

            template.merge(context, writer);
            result = writer.toString();

            return result;
        }
    }
}
