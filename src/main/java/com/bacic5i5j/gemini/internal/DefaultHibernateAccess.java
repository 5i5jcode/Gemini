/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.gemini.internal;

import com.bacic5i5j.gemini.database.Access;
import com.bacic5i5j.gemini.database.HibernateSessionFactory;
import com.google.inject.Inject;
import org.hibernate.Session;

import java.io.Serializable;

/**
 * @(#)DefaultHibernateAccess.java 1.0 10/03/2014
 */
public class DefaultHibernateAccess<T, PK extends Serializable> implements Access<T, PK> {
    @Inject
    private HibernateSessionFactory sessionFactory;

    private Class<T> persistClass;

    public DefaultHibernateAccess(Class<T> persistClass) {
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
}
