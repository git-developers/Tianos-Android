package xyz.elcomercio.software.dao.implement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import xyz.elcomercio.software.dao.dbConnection;
import xyz.elcomercio.software.dao.dbTables;
import xyz.elcomercio.software.dao.interfaces.ICategory;
import xyz.elcomercio.software.entity.Category;

public class CategoryDaoImplement extends dbConnection implements ICategory {

    public CategoryDaoImplement(Context context) {
        super(context);
    }

    public long insert(String username, Category object) {
        ContentValues values = new ContentValues();
        values.put(dbTables.USERNAME, username);
        values.put(dbTables.T_CATEGORY_CATEGORY_ID, object.getCategoryId());
        values.put(dbTables.ID, object.getId());
        values.put(dbTables.NAME, object.getName());

        return this
                .getSqliteDb()
                .insert(dbTables.T_CATEGORY, null, values)
                ;
    }

    public ArrayList<Category> findAll(String username) {

        ArrayList<Category> objects = new ArrayList();
        Cursor cursor = null;

        try {

            cursor = this.getSqliteDb()
                    .rawQuery("SELECT * FROM " + dbTables.T_CATEGORY + " ORDER BY " + dbTables.ID + " DESC", null);
            while (cursor.moveToNext()) {
                Category object = new Category();
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
        deleteTable(dbTables.T_CATEGORY);
    }

}
