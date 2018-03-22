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

    public long insert(String username, PointOfSale pointOfSale) {
        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, username);
        values.put(dbTables.ID, pointOfSale.getId());
        values.put(dbTables.NAME, pointOfSale.getName());

        return this
                .getSqliteDb()
                .insert(dbTables.T_POINT_OF_SALE, null, values)
                ;
    }

    public ArrayList<PointOfSale> findAll(String username) {

        ArrayList<PointOfSale> pointOfSales = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * FROM " + dbTables.T_POINT_OF_SALE + " ORDER BY " + dbTables.ID + " DESC", null);
            while (cursor.moveToNext()) {
                PointOfSale pointOfSale = new PointOfSale();
                pointOfSale.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                pointOfSale.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
                pointOfSales.add(pointOfSale);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return pointOfSales;
    }

}
