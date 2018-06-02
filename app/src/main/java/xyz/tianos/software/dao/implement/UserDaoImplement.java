package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import xyz.tianos.software.dao.dbConnection;
import xyz.tianos.software.dao.dbTables;
import xyz.tianos.software.dao.interfaces.IUser;
import xyz.tianos.software.entity.User;

public class UserDaoImplement extends dbConnection implements IUser {

    public UserDaoImplement(Context context) {
        super(context);
    }

    @Override
    public long insert(User object) {

        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, object.getUsername());
        values.put(dbTables.NAME, object.getName());
        values.put(dbTables.T_USER_LAST_NAME, object.getLast_name());
        values.put(dbTables.T_USER_EMAIL, object.getEmail());

        return this.getSqliteDb().insert(dbTables.T_USER, null, values);
    }

    @Override
    public User findOneById(String id) {
        User object = new User();
        Cursor cursor = null;

        try {
            cursor = this.getSqliteDb().rawQuery("SELECT * FROM " + dbTables.T_USER + " WHERE id=" + id, null);
            while (cursor.moveToNext()) {
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    @Override
    public User findOneByUsername(String username) {
        User object = new User();
        Cursor cursor = null;

        try {
            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * FROM " +
                            dbTables.T_USER + " WHERE " +
                            dbTables.USERNAME + "='" + username + "' LIMIT 1", null);
            while (cursor.moveToNext()) {
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
                object.setEmail(cursor.getString(cursor.getColumnIndex(dbTables.T_USER_EMAIL)));
                object.setRole(cursor.getString(cursor.getColumnIndex(dbTables.T_USER_ROLE)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    @Override
    public User findLastLogged() {
        User object = new User();
        Cursor cursor = null;

        try {
            cursor = this.getSqliteDb().rawQuery(
                    " SELECT * FROM " + dbTables.T_USER +
                    " ORDER BY " + dbTables.ID +
                    " DESC LIMIT 1", null);
            while (cursor.moveToNext()) {
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
                object.setEmail(cursor.getString(cursor.getColumnIndex(dbTables.T_USER_EMAIL)));
                object.setRole(cursor.getString(cursor.getColumnIndex(dbTables.T_USER_ROLE)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    public void deleteTable() {
        deleteTable(dbTables.T_USER);
    }

    public int deleteTableByUsername(String username) {
        return this
                .getSqliteDb()
                .delete(dbTables.T_USER,"username=?",new String[]{username})
                ;
    }

}
