/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.database;

import com.bacic5i5j.gemini.Gemini;
import com.bacic5i5j.gemini.logs.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * 这是一个默认实现的Hibernate会话管理，Gemini默认支持Hibernate
 *
 * @(#)HibernateSessionFactory.java 1.0 09/03/2014
 */
public class HibernateSessionFactory implements SessionContext<Session> {
    private final Logger logger = Gemini.instance.getLogger(HibernateSessionFactory.class);

    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    private SessionFactory sessionFactory;
    // 此处是硬编码
    private String configFile = "hibernate.cfg.xml";

    private static final HibernateSessionFactory hsf = new HibernateSessionFactory();

    private HibernateSessionFactory() {
        logger.info("Hibernate Session Factory init...");
        buildSessionFactory();
    }

    public static HibernateSessionFactory getInstance() {
        return hsf;
    }

    @Override
    public Session currentSession() {
        Session session = threadLocal.get();
        if (session == null || !session.isOpen() || !session.isConnected()) {
            buildSessionFactory();
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

    private void buildSessionFactory() {
        Configuration cfg = new AnnotationConfiguration();
        cfg.configure(configFile);
        sessionFactory = cfg.buildSessionFactory();
    }
}
