package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import xyz.tianos.software.dao.dbConnection;
import xyz.tianos.software.dao.dbTables;
import xyz.tianos.software.dao.interfaces.IProduct;
import xyz.tianos.software.entity.Product;

public class ProductDaoImplement extends dbConnection implements IProduct {

    public ProductDaoImplement(Context context) {
        super(context);
    }

    public long insert(String username, Product object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, username);
        values.put(dbTables.ID, object.getId());
        values.put(dbTables.NAME, object.getName());

        return this
                .getSqliteDb()
                .insert(dbTables.T_PRODUCT, null, values)
                ;
    }

    public ArrayList<Product> findAll(String username) {

        ArrayList<Product> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * FROM " + dbTables.T_PRODUCT + " ORDER BY " + dbTables.ID + " DESC", null);
            while (cursor.moveToNext()) {
                Product object = new Product();
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setName(cursor.getString(cursor.getColumnIndex(dbTables.NAME)));
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
        deleteTable(dbTables.T_PRODUCT);
    }

}
