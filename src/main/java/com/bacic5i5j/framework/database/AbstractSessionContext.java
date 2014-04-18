/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.database;

import com.bacic5i5j.framework.Gemini;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;

/**
 * @(#)AbstractSessionContext.java 1.0 14/03/2014
 */
public class AbstractSessionContext implements SessionContext<Session> {
    protected static final Logger logger = Gemini.instance.getLogger("Database connection");
    protected static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    protected SessionFactory sessionFactory;

    public boolean init(String config) {
        boolean success;

        logger.info("Hibernate会话管理生成器初始完成.");
        try {
            sessionFactory = new AnnotationConfiguration().configure(config).buildSessionFactory();
            success = true;
        } catch (Exception e) {
            logger.error("数据库连接会话初始错误: " + e.getMessage());
            success = false;
        }

        return success;
    }

    @Override
    public Session currentSession() {
        Session session = threadLocal.get();
        if (session == null || !session.isOpen() || !session.isConnected()) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }

        return session;
    }

    @Override
    public void closeSession() {
        Session session = threadLocal.get();
        if (session != null) {
            session.close();
        }
        threadLocal.set(null);
    }
}
