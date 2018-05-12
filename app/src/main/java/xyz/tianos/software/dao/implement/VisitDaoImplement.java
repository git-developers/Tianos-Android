package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import xyz.tianos.software.dao.dbConnection;
import xyz.tianos.software.dao.dbTables;
import xyz.tianos.software.dao.interfaces.IVisit;
import xyz.tianos.software.entity.Visit;
import xyz.tianos.software.utils.Utils;

public class VisitDaoImplement extends dbConnection implements IVisit {

    public VisitDaoImplement(Context context) {
        super(context);
    }

    public long insert(Visit object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, object.getUsername());
        values.put(dbTables.UUID, Utils.getUuid());
        values.put(dbTables.T_VISIT_START, object.getVisitStart());
        values.put(dbTables.T_VISIT_END, object.getVisitEnd());
        values.put(dbTables.T_VISIT_POINT_OF_SALE, object.getPointOfSaleId());

        return this
                .getSqliteDb()
                .insert(dbTables.T_VISIT, null, values)
                ;
    }

    public ArrayList<Visit> findAll(String username) {

        ArrayList<Visit> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_VISIT +
                            " WHERE " + dbTables.USERNAME + " = '" + username + "' " +
                            " ORDER BY " + dbTables.ID +
                            " DESC", null);

            while (cursor.moveToNext()) {
                Visit object = new Visit();
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setVisitStart(cursor.getString(cursor.getColumnIndex(dbTables.T_VISIT_START)));
                object.setVisitEnd(cursor.getString(cursor.getColumnIndex(dbTables.T_VISIT_END)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_VISIT_POINT_OF_SALE)));
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
        deleteTable(dbTables.CREATE_T_VISIT);
    }

}
