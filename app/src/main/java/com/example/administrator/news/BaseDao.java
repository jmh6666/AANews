package com.example.administrator.news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BaseDao<T> extends SQLiteOpenHelper {
    private Context mContext;
    private String mTableName;

    public BaseDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String tableName) {
        super(context, name, factory, version);
        mTableName = tableName;
        mContext = context;
    }

    protected abstract Map<String, String> getColNameTypeMap();
    protected abstract ContentValues getContentValues(T t);
    protected abstract T findByCursor(Cursor cursor);
    public static final String[] ALL_COLS = new String[]{"*"};
    protected static final String COL_TYPE_INT_PK_AUTOINCREMENT =
            "INTEGER PRIMARY KEY AUTOINCREMENT";
    protected static final String COL_TYPE_FLOAT = "FLOAT";
    protected static final String COL_TYPE_TEXT = "TEXT";
    protected static final String COL_TYPE_INT = "INT";
    protected static final String COL_TYPE_LONG = "LONG";
    protected static final String COL_TYPE_DOUBLE = "DOUBLE";

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, getColNameTypeMap());
    }

    private void createTable(SQLiteDatabase db, Map<String, String> colNameTypeMap) {
        StringBuilder sql = new StringBuilder()
                .append("CREATE TABLE ").append(mTableName).append("(");

        String[] keys = toArray(colNameTypeMap.keySet());
        for (String key : keys) {
            String colType = colNameTypeMap.get(key);
            sql.append(key).append(" ").append(colType).append(",");
        }
        sql.setLength(sql.length() - 1);
        sql.append(")");

        try {
            db.execSQL(sql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long save(T t) {
        ContentValues values = getContentValues(t);
        try {
            return getWritableDatabase().insert(mTableName, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void save(List<T> list) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            // TODO: 16/6/16 限制每次最多插入多少条
            for (T t : list) {
                save(t);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    protected long count(String selection, String[] selectionArgs) {
        Cursor cursor = null;
        try {
            String[] columns = new String[]{"COUNT(1)"};
            cursor = getWritableDatabase().query(
                    mTableName, columns, selection, selectionArgs, null, null, null);
            if (cursor == null)
                return 0L;

            if (cursor.moveToFirst())
                return cursor.getInt(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(cursor);
        }
        return 0L;
    }

    public long count() {
        return count(null, null);
    }

    protected List<T> findList(String[] columns, String selection, String[] selectionArgs,
                               String groupBy, String having, String orderBy, String limit) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().query(
                    mTableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            return findListByCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(cursor);
        }

        return new ArrayList<>();
    }

    protected List<T> findListByCursor(Cursor cursor) {
        if (cursor == null)
            return new ArrayList<>();

        List<T> list = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                T t = findByCursor(cursor);
                if (t != null) {
                    list.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(cursor);
        }
        return list;
    }

    public List<T> findAll() {
        return findList(ALL_COLS, null, null, null, null, null, null);
    }

    protected int update(ContentValues values, String selection, String[] whereArgs) {
        try {
            return getWritableDatabase().update(mTableName, values, selection, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected int delete(String selection, String[] selectionArgs) {
        try {
            return getWritableDatabase().delete(mTableName, selection, selectionArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected void deleteAll() {
        delete(null, null);
    }

    protected String[] toArray(Collection<String> collection) {
        if (collection == null)
            return new String[]{};

        int size = collection.size();
        return collection.toArray(new String[size]);
    }

    protected void close(Cursor cursor) {
        if (cursor == null)
            return;

        try {
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
