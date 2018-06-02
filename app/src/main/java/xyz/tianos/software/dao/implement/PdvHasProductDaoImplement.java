package xyz.tianos.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import xyz.tianos.software.dao.dbConnection;
import xyz.tianos.software.dao.dbTables;
import xyz.tianos.software.dao.interfaces.IPdvHasProduct;
import xyz.tianos.software.entity.PdvHasProduct;
import xyz.tianos.software.utils.Utils;

public class PdvHasProductDaoImplement extends dbConnection implements IPdvHasProduct {

    public PdvHasProductDaoImplement(Context context) {
        super(context);
    }

    public long insert(PdvHasProduct object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, object.getUsername());
        values.put(dbTables.UUID, Utils.getUuid());
        values.put(dbTables.T_POINT_OF_SALE_ID, object.getPointOfSaleId());
        values.put(dbTables.T_PRODUCT_ID, object.getProductId());
        values.put(dbTables.T_PDV_HAS_PRODUCT_CANTIDAD_PRODUCT, object.getQuantity());

        return this
                .getSqliteDb()
                .insert(dbTables.T_PDV_HAS_PRODUCT, null, values)
                ;
    }

    public long updateIdBackend(PdvHasProduct object) {

        ContentValues values = new ContentValues();

        values.put(dbTables.ID_BACKEND, object.getIdBackend());

        return this
                .getSqliteDb()
                .update(dbTables.T_PDV_HAS_PRODUCT, values, "uuid = '" + object.getUuid() + "' ",null)
                ;
    }

    public ArrayList<PdvHasProduct> findAll(String username) {

        ArrayList<PdvHasProduct> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_PDV_HAS_PRODUCT +
                            " WHERE " + dbTables.USERNAME + " = '" + username + "' " +
                            " ORDER BY " + dbTables.ID +
                            " DESC", null);

            while (cursor.moveToNext()) {
                PdvHasProduct object = new PdvHasProduct();
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_POINT_OF_SALE_ID)));
                object.setProductId(cursor.getInt(cursor.getColumnIndex(dbTables.T_PRODUCT_ID)));
                object.setQuantity(cursor.getInt(cursor.getColumnIndex(dbTables.T_PDV_HAS_PRODUCT_CANTIDAD_PRODUCT)));
                objects.add(object);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return objects;
    }

    public ArrayList<PdvHasProduct> findAllIdBackendNull(String username) {

        ArrayList<PdvHasProduct> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_PDV_HAS_PRODUCT +
                            " WHERE " + dbTables.USERNAME + " = '" + username + "' " +
                            " AND " + dbTables.ID_BACKEND + " IS NULL " +
                            " ORDER BY " + dbTables.ID +
                            " DESC", null);

            while (cursor.moveToNext()) {
                PdvHasProduct object = new PdvHasProduct();
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_POINT_OF_SALE_ID)));
                object.setProductId(cursor.getInt(cursor.getColumnIndex(dbTables.T_PRODUCT_ID)));
                object.setQuantity(cursor.getInt(cursor.getColumnIndex(dbTables.T_PDV_HAS_PRODUCT_CANTIDAD_PRODUCT)));
                objects.add(object);
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return objects;
    }

    public PdvHasProduct findOneByUuid(String uuid) {

        PdvHasProduct object = new PdvHasProduct();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * " +
                            " FROM " + dbTables.T_PDV_HAS_PRODUCT +
                            " WHERE " + dbTables.UUID + " = '" + uuid + "' " +
                            " LIMIT 1", null);

            while (cursor.moveToNext()) {
                object.setId(cursor.getInt(cursor.getColumnIndex(dbTables.ID)));
                object.setUsername(cursor.getString(cursor.getColumnIndex(dbTables.USERNAME)));
                object.setUuid(cursor.getString(cursor.getColumnIndex(dbTables.UUID)));
                object.setPointOfSaleId(cursor.getInt(cursor.getColumnIndex(dbTables.T_POINT_OF_SALE_ID)));
                object.setProductId(cursor.getInt(cursor.getColumnIndex(dbTables.T_PRODUCT_ID)));
                object.setQuantity(cursor.getInt(cursor.getColumnIndex(dbTables.T_PDV_HAS_PRODUCT_CANTIDAD_PRODUCT)));
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return object;
    }

    public void deleteTable() {
        deleteTable(dbTables.CREATE_T_PDV_HAS_PRODUCT);
    }

}
