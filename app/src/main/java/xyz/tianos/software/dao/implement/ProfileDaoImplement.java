package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import xyz.tianos.software.dao.dbConnection;
import xyz.tianos.software.dao.dbTables;
import xyz.tianos.software.dao.interfaces.IProfile;
import xyz.tianos.software.entity.Profile;

public class ProfileDaoImplement extends dbConnection implements IProfile {

    public ProfileDaoImplement(Context context) {
        super(context);
    }

    @Override
    public long insert(String username, Profile object) {

        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, username);
        values.put(dbTables.ID, object.getId());
        values.put(dbTables.CODE, object.getCode());
        values.put(dbTables.NAME, object.getName());
        return this.getSqliteDb().insert(dbTables.T_PROFILE, null, values);
    }

    @Override
    public Profile findOneById(String id) {
        Profile object = new Profile();
        Cursor cursor = null;

        try {
            cursor = this.getSqliteDb().rawQuery("SELECT * FROM " + dbTables.T_PROFILE + " WHERE id=" + id, null);
            while (cursor.moveToNext()) {
//                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
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
    public Profile findOneByUsername(String username) {
        Profile object = new Profile();
        Cursor cursor = null;

        try {
            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * FROM " +
                            dbTables.T_PROFILE + " WHERE " +
                            dbTables.USERNAME + "='" + username + "' LIMIT 1", null);
            while (cursor.moveToNext()) {
//                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
//                object.setEmail(cursor.getString(cursor.getColumnIndex(dbTables.T_PROFILE_EMAIL)));
//                object.setRole(cursor.getString(cursor.getColumnIndex(dbTables.T_PROFILE_ROLE)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    public void deleteTable() {
        deleteTable(dbTables.T_PROFILE);
    }

}
