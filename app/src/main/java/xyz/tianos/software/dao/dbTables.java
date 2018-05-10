package xyz.tianos.software.dao;

import xyz.tianos.software.activity.BuildConfig;

public class dbTables {

    public static String DB_NAME = "tianos.db";
    public static int DB_VERSION = BuildConfig.VERSION_CODE;

    public static String ID = "id";
    public static String UUID = "uuid";
    public static String NAME = "name";
    public static String CODE = "code";
    public static String SLUG = "slug";
    public static String USERNAME = "username";
    public static String CREATED_AT = "created_at";

    public static String T_USER = "t_user";
    public static String T_USER_LAST_NAME = "last_name";
    public static String T_USER_EMAIL = "email";
    public static String T_USER_ROLE = "role";

    public static String T_PROFILE = "t_profile";
    public static String T_PROFILE_ID = "t_profile_id";
    public static String T_ROLE = "t_role";

    public static String T_POINT_OF_SALE = "t_point_of_sale";
    public static String T_POINT_OF_SALE_LATITUDE = "latitude";
    public static String T_POINT_OF_SALE_LONGITUDE = "longitude";

    public static String T_CATEGORY = "t_category";
    public static String T_CATEGORY_CATEGORY_ID = "category_id";

    public static String T_PRODUCT = "t_product";

    public static String T_BREADCRUMB = "t_breadcrumb";
    public static String T_BREADCRUMB_POINTOFSALE_ID = "point_of_sale_id";
    public static String T_BREADCRUMB_CATEGORY_ID = "category_id";

    public static String T_VISIT = "t_visit";
    public static String T_VISIT_START = "visit_start";
    public static String T_VISIT_END = "visit_end";
    public static String T_VISIT_POINT_OF_SALE = "point_of_sale";

    public static String CREATE_T_VISIT =
        "CREATE TABLE " + T_VISIT + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            T_VISIT_START + " VARCHAR(50)," +
            T_VISIT_END + " VARCHAR(50)," +
            UUID + " VARCHAR(50)," +
            T_VISIT_POINT_OF_SALE + " INTEGER(11)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

    public static String CREATE_T_PROFILE =
        "CREATE TABLE " + T_PROFILE + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CODE + " VARCHAR(50)," +
            NAME + " VARCHAR(250)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

    public static String CREATE_T_ROLE =
        "CREATE TABLE " + T_ROLE + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            T_PROFILE_ID + " INTEGER(11)," +
            NAME + " VARCHAR(250)," +
            CODE + " VARCHAR(50)," +
            SLUG + " VARCHAR(200)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

    public static String CREATE_T_USER =
        "CREATE TABLE " + T_USER + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME + " VARCHAR(250)," +
            CODE + " VARCHAR(50)," +
            T_USER_LAST_NAME + " VARCHAR(200)," +
            T_USER_EMAIL + " VARCHAR(200)," +
            T_USER_ROLE + " VARCHAR(50)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

    public static String CREATE_T_POINT_OF_SALE =
        "CREATE TABLE " + T_POINT_OF_SALE + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CODE + " VARCHAR(50)," +
            NAME + " VARCHAR(250)," +
            T_POINT_OF_SALE_LATITUDE + " REAL(30)," +
            T_POINT_OF_SALE_LONGITUDE + " REAL(30)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

    public static String CREATE_T_CATEGORY =
        "CREATE TABLE " + T_CATEGORY + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CODE + " VARCHAR(50)," +
            NAME + " VARCHAR(250)," +
            T_CATEGORY_CATEGORY_ID + " INT(11)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

    public static String CREATE_T_PRODUCT =
        "CREATE TABLE " + T_PRODUCT + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            CODE + " VARCHAR(50)," +
            NAME + " VARCHAR(250)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

    public static String CREATE_T_BREADCRUMB =
        "CREATE TABLE " + T_BREADCRUMB + " (" +
            USERNAME + " VARCHAR(50)," +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            UUID + " VARCHAR(50)," +
            T_BREADCRUMB_POINTOFSALE_ID + " INTEGER(11)," +
            T_BREADCRUMB_CATEGORY_ID + " INTEGER(11)," +
            CREATED_AT + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"
    ;

}
