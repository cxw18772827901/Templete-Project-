package com.greendao.db.helper;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.greendao.db.bean.DemoBean;

import com.greendao.db.helper.DemoBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig demoBeanDaoConfig;

    private final DemoBeanDao demoBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        demoBeanDaoConfig = daoConfigMap.get(DemoBeanDao.class).clone();
        demoBeanDaoConfig.initIdentityScope(type);

        demoBeanDao = new DemoBeanDao(demoBeanDaoConfig, this);

        registerDao(DemoBean.class, demoBeanDao);
    }
    
    public void clear() {
        demoBeanDaoConfig.clearIdentityScope();
    }

    public DemoBeanDao getDemoBeanDao() {
        return demoBeanDao;
    }

}