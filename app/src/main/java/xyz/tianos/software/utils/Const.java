package xyz.tianos.software.utils;

/**
 * Created by jafeth on 3/31/17.
 */

public class Const {

    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_WARNING = 2;
    public static final int STATUS_ERROR = 3;

    public static final boolean USER_IS_LOGGED = true;
    public static final boolean USER_IS_NOT_LOGGED = false;

    public static final int VERSION = 3;
    public static final int HTTP_STATUS_200 = 200;
    public static final int SOCKET_TIMEOUT = 4000;
    public static final String IS_LOGGED = "is_logged";
    public static final String LAST_ACTIVITY = "last_activity";

    public static final String API_BASE_ROUTE = "http://elcomercio-circulacion.press/api/";
    public static final String ROUTE_LOGIN = "user/login";
    public static final String ROUTE_PRODUCT = "product/";
    public static final String ROUTE_CATEGORY = "category/";
    public static final String ROUTE_VISIT_START = "visit/start";
    public static final String ROUTE_VISIT_END = "visit/end";
    public static final String ROUTE_POINT_OF_SALE = "point-of-sale/";
    public static final String ROUTE_POINT_OF_SALE_HAS_PRODUCT = "pdv-has-product/create";

    public static final String CONTENT_TYPE = "application/json";
    public static final String PREFERENCES = "preferences";

    public static final String DATA_USER = "DATA_USER";
    public static final String DATA_VISIT = "DATA_VISIT";
    public static final String DATA_POINT_OF_SALE = "DATA_POINT_OF_SALE";

    public static final int ACTIVITY_LOGIN = 1;
    public static final int ACTIVITY_POINT_OF_SALE = 2;
    public static final int ACTIVITY_CATEGORY = 3;
    public static final int ACTIVITY_API = 4;
    public static final int ACTIVITY_VISIT_START = 5;
    public static final int ACTIVITY_VISIT_END = 6;
    public static final int ACTIVITY_PDV_HAS_PRODUCT = 7;

    public static final String DEF_TYPE_DRAWABLE = "drawable";

}
