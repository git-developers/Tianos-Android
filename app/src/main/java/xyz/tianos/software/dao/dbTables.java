package xyz.tianos.software.dao;

import xyz.tianos.software.activity.BuildConfig;

public class dbTables {

    public static String DB_NAME = "tianos.db";
    public static int DB_VERSION = BuildConfig.VERSION_CODE;

    public static String ID = "id";
    public static String NAME = "name";
    public static String CODE = "code";
    public static String CREATED_AT = "created_at";
    public static String USERNAME = "username";

    public static String T_USER = "t_user";
    public static String T_USER_LAST_NAME = "last_name";
    public static String T_USER_EMAIL = "email";
    public static String T_USER_ROLE = "role";

    public static String T_POINT_OF_SALE = "t_point_of_sale";
    public static String T_POINT_OF_SALE_LATITUDE = "latitude";
    public static String T_POINT_OF_SALE_LONGITUDE = "longitude";


    public static String CREATE_T_USER =
        "CREATE TABLE " + T_USER + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " VARCHAR(250)," +
            CODE + " VARCHAR(50)," +
            USERNAME + " VARCHAR(50)," +
            T_USER_LAST_NAME + " VARCHAR(200)," +
            T_USER_EMAIL + " VARCHAR(200)," +
            T_USER_ROLE + " VARCHAR(50)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
            ;

    public static String CREATE_T_POINT_OF_SALE =
        "CREATE TABLE " + T_POINT_OF_SALE + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CODE + " VARCHAR(50)," +
            NAME + " VARCHAR(250)," +
            USERNAME + " VARCHAR(50)," +
            T_POINT_OF_SALE_LATITUDE + " VARCHAR(30)," +
            T_POINT_OF_SALE_LONGITUDE + " VARCHAR(30)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
            ;

}
