package xyz.tianos.software.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class dbSQLiteHelper extends SQLiteOpenHelper {

    private String DROP_TABLE;

    public dbSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.DROP_TABLE = "DROP TABLE IF EXISTS ";
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbTables.CREATE_T_USER);
        db.execSQL(dbTables.CREATE_T_ROLE);
        db.execSQL(dbTables.CREATE_T_PROFILE);
        db.execSQL(dbTables.CREATE_T_PRODUCT);
        db.execSQL(dbTables.CREATE_T_CATEGORY);
        db.execSQL(dbTables.CREATE_T_BREADCRUMB);
        db.execSQL(dbTables.CREATE_T_POINT_OF_SALE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(this.DROP_TABLE + dbTables.T_USER);
        db.execSQL(this.DROP_TABLE + dbTables.T_PRODUCT);
        db.execSQL(this.DROP_TABLE + dbTables.T_PROFILE);
        db.execSQL(this.DROP_TABLE + dbTables.T_CATEGORY);
        db.execSQL(this.DROP_TABLE + dbTables.T_BREADCRUMB);
        db.execSQL(this.DROP_TABLE + dbTables.T_POINT_OF_SALE);
        onCreate(db);
    }
}
