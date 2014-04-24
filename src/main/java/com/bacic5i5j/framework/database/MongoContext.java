/**
 * Copyright (c) 2014, 5i5j.com. All rights reserved.
 * 5i5j.com. Use is subject to license terms.
 */
package com.bacic5i5j.framework.database;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB的会话维持模块，一个mongodb的会话上下文维持一个库的连接
 *
 * @(#)MongoContext.java 1.0 23/04/2014
 */
public class MongoContext implements SessionContext<DB> {
    private String dbname;
    private MongoClient mongoClient;

    private ThreadLocal<DB> threadLocal;

    public void init(String db, String username, String password, List servers) {
        this.dbname = db;

        MongoCredential mc = MongoCredential.createMongoCRCredential(username, dbname, password.toCharArray());
        List mcs = new ArrayList();
        mcs.add(mc);

        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(100)
                .connectTimeout(10*1000)
                .readPreference(ReadPreference.secondaryPreferred())
                .build();

        this.mongoClient = new MongoClient(servers, mcs, options);
        this.threadLocal = new ThreadLocal<DB>();
    }

    public DB currentSession() {
        DB db = threadLocal.get();
        if (db == null) {
            db = mongoClient.getDB(dbname);
            threadLocal.set(db);
        }

        return db;
    }

    @Override
    public void closeSession() {
        threadLocal.set(null);
    }
}
