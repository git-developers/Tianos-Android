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
        values.put(dbTables.T_VISIT_POINT_OF_SALE, object.getPointOfSaleId());

        return this
                .getSqliteDb()
                .insert(dbTables.T_VISIT, null, values)
                ;
    }

    public long updateIdBackendStart(Visit object) {

        ContentValues values = new ContentValues();

        values.put(dbTables.T_VISIT_ID_BACKEND_START, object.getIdBackend());

        return this
                .getSqliteDb()
                .update(dbTables.T_VISIT, values, dbTables.UUID +  " = '" + object.getUuid() + "' ",null)
                ;
    }

    public long updateIdBackendEnd(Visit object) {

        ContentValues values = new ContentValues();

        values.put(dbTables.T_VISIT_ID_BACKEND_END, object.getIdBackend());

        return this
                .getSqliteDb()
                .update(dbTables.T_VISIT, values, dbTables.UUID +  " = '" + object.getUuid() + "' ",null)
                ;
    }

    public long updateVisitEnd(Visit object) {

        ContentValues values = new ContentValues();

        values.put(dbTables.T_VISIT_END, object.getVisitEnd());

        return this
                .getSqliteDb()
                .update(dbTables.T_VISIT, values, dbTables.ID + " = '" + object.getId() + "' ",null)
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

    public ArrayList<Visit> findAllListStart(String username) {

        ArrayList<Visit> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_VISIT +
                            " WHERE " + dbTables.USERNAME + " = '" + username + "' " +
                            " AND " + dbTables.T_VISIT_ID_BACKEND_START + " IS NULL " +
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

    public ArrayList<Visit> findAllListEnd(String username) {

        ArrayList<Visit> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_VISIT +
                            " WHERE " + dbTables.USERNAME + " = '" + username + "' " +
                            " AND " + dbTables.T_VISIT_ID_BACKEND_END + " IS NULL " +
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

    public Visit findOneByUuid(String uuid) {

        Visit object = new Visit();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_VISIT +
                            " WHERE " + dbTables.UUID + " = '" + uuid + "' " +
                            " LIMIT 1", null);

            while (cursor.moveToNext()) {
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setVisitStart(cursor.getString(cursor.getColumnIndex(dbTables.T_VISIT_START)));
                object.setVisitEnd(cursor.getString(cursor.getColumnIndex(dbTables.T_VISIT_END)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_VISIT_POINT_OF_SALE)));
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    public Visit findLast() {

        Visit object = new Visit();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_VISIT +
                            " ORDER BY " + dbTables.ID + " DESC " +
                            " LIMIT 1", null);

            while (cursor.moveToNext()) {
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setVisitStart(cursor.getString(cursor.getColumnIndex(dbTables.T_VISIT_START)));
                object.setVisitEnd(cursor.getString(cursor.getColumnIndex(dbTables.T_VISIT_END)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_VISIT_POINT_OF_SALE)));
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    public void deleteTable() {
        deleteTable(dbTables.CREATE_T_VISIT);
    }

}
