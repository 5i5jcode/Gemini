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

/**
 * 这是一个默认实现的Hibernate会话管理，Gemini默认支持Hibernate
 *
 * @(#)HibernateSessionFactory.java 1.0 09/03/2014
 */
public class HibernateSessionFactory implements SessionContext<Session> {
    private final Logger logger = Gemini.instance.getLogger(HibernateSessionFactory.class);

    private final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    private SessionFactory sessionFactory;

    public HibernateSessionFactory() {
        logger.info("Hibernate Session Factory init...");
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Hibernate build session factory success.");
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
