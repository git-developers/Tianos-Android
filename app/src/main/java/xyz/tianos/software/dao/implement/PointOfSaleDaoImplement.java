package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import xyz.tianos.software.dao.dbConnection;
import xyz.tianos.software.dao.dbTables;
import xyz.tianos.software.dao.interfaces.IPointOfSale;
import xyz.tianos.software.entity.PointOfSale;

public class PointOfSaleDaoImplement extends dbConnection implements IPointOfSale {

    public PointOfSaleDaoImplement(Context context) {
        super(context);
    }

    public long insert(String username, PointOfSale object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, username);
        values.put(dbTables.ID, object.getId());
        values.put(dbTables.NAME, object.getName());
        values.put(dbTables.T_POINT_OF_SALE_LATITUDE, object.getLatitude());
        values.put(dbTables.T_POINT_OF_SALE_LONGITUDE, object.getLongitude());

        return this
                .getSqliteDb()
                .insert(dbTables.T_POINT_OF_SALE, null, values)
                ;
    }

    public PointOfSale findOneById(String id) {

        PointOfSale object = new PointOfSale();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_POINT_OF_SALE +
                            " WHERE id= '" + id + "'", null);

            while (cursor.moveToNext()) {
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
                object.setLatitude(cursor.getString(cursor.getColumnIndex(dbTables.T_POINT_OF_SALE_LATITUDE)));
                object.setLongitude(cursor.getString(cursor.getColumnIndex(dbTables.T_POINT_OF_SALE_LONGITUDE)));
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    public ArrayList<PointOfSale> findAll(String username) {

        ArrayList<PointOfSale> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * FROM " + dbTables.T_POINT_OF_SALE + " ORDER BY " + dbTables.ID + " DESC", null);
            while (cursor.moveToNext()) {
                PointOfSale object = new PointOfSale();
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
                object.setLatitude(cursor.getString(cursor.getColumnIndex(dbTables.T_POINT_OF_SALE_LATITUDE)));
                object.setLongitude(cursor.getString(cursor.getColumnIndex(dbTables.T_POINT_OF_SALE_LONGITUDE)));
                objects.add(object);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return objects;
    }

    public void deleteTable() {
        deleteTable(dbTables.T_POINT_OF_SALE);
    }

}
