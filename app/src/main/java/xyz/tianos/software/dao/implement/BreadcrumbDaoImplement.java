package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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

    public long insert(Breadcrumb object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.UUID, Utils.getUuid());
        values.put(dbTables.USERNAME, object.getUsername());
        values.put(dbTables.T_BREADCRUMB_POINTOFSALE_ID, object.getPointOfSaleId());

        return this
                .getSqliteDb()
                .insert(dbTables.T_BREADCRUMB, null, values)
                ;
    }

    public long update(Breadcrumb object) {

        Breadcrumb bc = findLast();

        ContentValues values = new ContentValues();

/*        if(bc.getUuid() != null){
            values.put(dbTables.UUID, Utils.getUuid());
        }

        if(bc.getPointOfSaleId() > 0){
            values.put(dbTables.T_BREADCRUMB_POINTOFSALE_ID, object.getId());
        }*/

        if(bc.getCategoryId() > 0){
            values.put(dbTables.T_BREADCRUMB_CATEGORY_ID, object.getCategoryId());
        }

        return this
                .getSqliteDb()
                .update(dbTables.T_BREADCRUMB, values, "username='" + bc.getUsername() + "'",null)
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
                    .rawQuery("SELECT " +
                            "t1.id, " +
                            "t1.username, " +
                            "t1.uuid, " +
                            "t1.point_of_sale_id, " +
                            "t1.category_id, " +

                            "t2.id pdv_id, " +
                            "t2.name pdv_name, " +

                            "t3.id category_obj_id, " +
                            "t3.name category_obj_name " +

                            " FROM " + dbTables.T_BREADCRUMB + " AS t1 " +
                            " LEFT JOIN " + dbTables.T_POINT_OF_SALE + " AS t2 on t2.id = t1.point_of_sale_id " +
                            " LEFT JOIN " + dbTables.T_CATEGORY + " AS t3 on t3.id = t1.category_id " +
                            " ORDER BY t1." + dbTables.ID + " DESC " +
                            " LIMIT 1", null);

            while (cursor.moveToNext()) {

                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));

                PointOfSale pointOfSale = new PointOfSale();
                pointOfSale.setId( cursor.getInt(cursor.getColumnIndex("pdv_id")) );
                pointOfSale.setName( cursor.getString(cursor.getColumnIndex("pdv_name")) );
                object.setPointOfSale(pointOfSale);

                Category category = new Category();
                category.setId( cursor.getInt(cursor.getColumnIndex("category_obj_id")) );
                category.setName( cursor.getString(cursor.getColumnIndex("category_obj_name")) );
                object.setCategory(category);
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
