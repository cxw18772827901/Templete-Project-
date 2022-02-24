package com.greendao.db.helper;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.greendao.db.bean.CityBean.Converter;
import java.util.List;

import com.greendao.db.bean.CityBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CITY_BEAN".
*/
public class CityBeanDao extends AbstractDao<CityBean, Long> {

    public static final String TABLENAME = "CITY_BEAN";

    /**
     * Properties of entity CityBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property _id = new Property(0, Long.class, "_id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property City = new Property(2, String.class, "city", false, "CITY");
    }

    private final Converter cityConverter = new Converter();

    public CityBeanDao(DaoConfig config) {
        super(config);
    }
    
    public CityBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CITY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: _id
                "\"NAME\" TEXT," + // 1: name
                "\"CITY\" TEXT);"); // 2: city
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CITY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CityBean entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        List city = entity.getCity();
        if (city != null) {
            stmt.bindString(3, cityConverter.convertToDatabaseValue(city));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CityBean entity) {
        stmt.clearBindings();
 
        Long _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        List city = entity.getCity();
        if (city != null) {
            stmt.bindString(3, cityConverter.convertToDatabaseValue(city));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CityBean readEntity(Cursor cursor, int offset) {
        CityBean entity = new CityBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cityConverter.convertToEntityProperty(cursor.getString(offset + 2)) // city
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CityBean entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCity(cursor.isNull(offset + 2) ? null : cityConverter.convertToEntityProperty(cursor.getString(offset + 2)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CityBean entity, long rowId) {
        entity.set_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CityBean entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CityBean entity) {
        return entity.get_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}