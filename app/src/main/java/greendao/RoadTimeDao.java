package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ROAD_TIME".
*/
public class RoadTimeDao extends AbstractDao<RoadTime, String> {

    public static final String TABLENAME = "ROAD_TIME";

    /**
     * Properties of entity RoadTime.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Road_name = new Property(0, String.class, "road_name", true, "ROAD_NAME");
        public final static Property Stop_time = new Property(1, Integer.class, "stop_time", false, "STOP_TIME");
    };


    public RoadTimeDao(DaoConfig config) {
        super(config);
    }
    
    public RoadTimeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ROAD_TIME\" (" + //
                "\"ROAD_NAME\" TEXT PRIMARY KEY NOT NULL ," + // 0: road_name
                "\"STOP_TIME\" INTEGER);"); // 1: stop_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ROAD_TIME\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RoadTime entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getRoad_name());
 
        Integer stop_time = entity.getStop_time();
        if (stop_time != null) {
            stmt.bindLong(2, stop_time);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RoadTime readEntity(Cursor cursor, int offset) {
        RoadTime entity = new RoadTime( //
            cursor.getString(offset + 0), // road_name
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1) // stop_time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RoadTime entity, int offset) {
        entity.setRoad_name(cursor.getString(offset + 0));
        entity.setStop_time(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(RoadTime entity, long rowId) {
        return entity.getRoad_name();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(RoadTime entity) {
        if(entity != null) {
            return entity.getRoad_name();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}