package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import xyz.tianos.software.dao.dbConnection;
import xyz.tianos.software.dao.dbTables;
import xyz.tianos.software.dao.interfaces.IBreadcrumb;
import xyz.tianos.software.entity.Breadcrumb;
import xyz.tianos.software.entity.Category;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.entity.User;
import xyz.tianos.software.utils.Utils;

public class BreadcrumbDaoImplement extends dbConnection implements IBreadcrumb {

    public BreadcrumbDaoImplement(Context context) {
        super(context);
    }

    public long save_1_User(User object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, object.getUsername());
        values.put(dbTables.UUID, Utils.getUuid());

//        values.put(dbTables.T_BREADCRUMB_POINTOFSALE_ID, object.getPointOfSaleId());
//        values.put(dbTables.T_BREADCRUMB_CATEGORY_ID, object.getCategoryId());

        return this
                .getSqliteDb()
                .insert(dbTables.T_BREADCRUMB, null, values)
                ;
    }

    public long save_2_PointOfSale(PointOfSale object) {

        Breadcrumb breadcrumb = findLast();


        ContentValues values = new ContentValues();
        values.put(dbTables.T_BREADCRUMB_POINTOFSALE_ID, object.getId());
//        values.put(dbTables.T_BREADCRUMB_CATEGORY_ID, object.getCategoryId());

        return this
                .getSqliteDb()
                .insert(dbTables.T_BREADCRUMB, null, values)
                ;
    }

    public long save_3_Category(Category object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.T_BREADCRUMB_CATEGORY_ID, object.getId());

        return this
                .getSqliteDb()
                .insert(dbTables.T_BREADCRUMB, null, values)
                ;
    }

    public ArrayList<Breadcrumb> findAll(String username) {

        ArrayList<Breadcrumb> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_BREADCRUMB +
                            " WHERE " + dbTables.USERNAME + " = '" + username + "' " +
                            " ORDER BY " + dbTables.ID +
                            " DESC", null);
            while (cursor.moveToNext()) {
                Breadcrumb object = new Breadcrumb();
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_BREADCRUMB_POINTOFSALE_ID)));
                object.setCategoryId(cursor.getInt(cursor.getColumnIndex(dbTables.T_BREADCRUMB_CATEGORY_ID)));
                objects.add(object);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return objects;
    }

    public Breadcrumb findLast() {

        Breadcrumb object = new Breadcrumb();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_BREADCRUMB +
                            " ORDER BY " + dbTables.ID +
                            " DESC LIMIT 1", null);

            while (cursor.moveToNext()) {
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_BREADCRUMB_POINTOFSALE_ID)));
                object.setCategoryId(cursor.getInt(cursor.getColumnIndex(dbTables.T_BREADCRUMB_CATEGORY_ID)));
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    public void deleteTable() {
        deleteTable(dbTables.T_BREADCRUMB);
    }

}
