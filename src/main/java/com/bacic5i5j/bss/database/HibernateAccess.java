/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.bss.database;

import com.bacic5i5j.bss.Gemini;
import org.hibernate.Session;
import org.slf4j.Logger;

import java.io.Serializable;
import java.util.List;

/**
 * @(#)HibernateAccess.java 1.0 14/03/2014
 */
public class HibernateAccess<T, PK extends Serializable> implements Access<T, PK> {
    protected final Logger logger;

    private Class<T> persistClass;

    protected SessionContext<Session> sessionFactory;

    public HibernateAccess(SessionContext sessionFactory) {
        // 初始化会话上下文
        this.sessionFactory = sessionFactory;
        logger = Gemini.instance.getLogger(this.getClass());
    }

    @Override
    public void setClass(Class<T> persistClass) {
        this.persistClass = persistClass;
    }

    @Override
    public T get(PK id) {
        Session session = sessionFactory.currentSession();
        Object obj = session.get(persistClass, id);
        sessionFactory.closeSession();

        if (obj == null) {
            return null;
        }

        return (T) obj;
    }

    @Override
    public PK save(T entity) {
        Session session = sessionFactory.currentSession();
        Serializable id = session.save(entity);
        session.flush();
        sessionFactory.closeSession();

        if (id == null) {
            return null;
        }
        return (PK) id;
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.currentSession();
        session.delete(entity);
        session.flush();
        sessionFactory.closeSession();
    }

    @Override
    public T delete(PK id) {
        T obj = get(id);
        if (obj == null) {
            return null;
        }

        delete(obj);
        return obj;
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.currentSession();
        session.update(entity);
        session.flush();
        sessionFactory.closeSession();
    }

    @Override
    public List<T> all() {
        Session session = sessionFactory.currentSession();
        List list = session.createCriteria(persistClass).list();
        sessionFactory.currentSession();

        return list;
    }

    @Override
    public List<T> page(int start, int max) {
        Session session = sessionFactory.currentSession();
        List list = session.createCriteria(persistClass).setFirstResult(start).setMaxResults(max).list();
        sessionFactory.closeSession();

        return list;
    }
}
