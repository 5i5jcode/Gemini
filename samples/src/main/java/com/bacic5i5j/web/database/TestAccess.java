/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.web.database;

import com.bacic5i5j.framework.database.HibernateAccess;
import com.bacic5i5j.framework.database.SessionContext;
import com.bacic5i5j.web.entity.User;
import com.bacic5i5j.web.inject.MySQLDB;
import com.google.inject.Inject;

/**
 * @(#)TestAccess.java 1.0 15/03/2014
 */
public class TestAccess extends HibernateAccess {

    @Inject
    public TestAccess(@MySQLDB SessionContext sessionFactory) {
        super(sessionFactory);
        setClass(User.class);
    }
}
