package xyz.tianos.software.utils;

/**
 * Created by jafeth on 3/31/17.
 */

public class Const {

    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_WARNING = 2;
    public static final int STATUS_ERROR = 3;

    public static final int VERSION = 3;
    public static final int HTTP_STATUS_200 = 200;
    public static final int SOCKET_TIMEOUT = 4000;
    public static final String IS_LOGGED = "is_logged";
    public static final String LAST_ACTIVITY = "last_activity";

    public static final String API_BASE_ROUTE = "http://tianos.xyz/api/";
    public static final String ROUTE_LOGIN = "user/login";
    public static final String ROUTE_PRODUCT = "product/";
    public static final String ROUTE_CATEGORY = "category/";
    public static final String ROUTE_POINT_OF_SALE = "point-of-sale/";
//    public static final String ROUTE_LIST_POINT_OF_SALE = API_BASE_ROUTE + "point-of-sale/";
//    public static final String ROUTE_REPORTS_PIECHART = API_BASE_ROUTE + "reports/piechart";

    public static final String CONTENT_TYPE = "application/json";
    public static final String PREFERENCES = "preferences";

    public static final String PARAMETER_USERNAME = "username";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_REGISTRATION_ID = "registration_id";

    public static final String JSON_KEY_USER = "user";

    public static final int ACTIVITY_LOGIN = 1;
    public static final int ACTIVITY_POINT_OF_SALE = 2;
    public static final int ACTIVITY_CATEGORY = 3;

    public static final String BUNDLE_ROUTE = "route";
    public static final String BUNDLE_DATE_SELECTED = "date_selected";

    public static final int ROUTE_REPORTS_PIECHART_INT = 1;
    public static final int ROUTE_REPORTS_STEPPEDAREACHART_INT = 2;
    public static final int ROUTE_REPORTS_GAUGE_INT = 3;

    public static final String PENDIENTE = "pendiente";
    public static final String NO_PENDIENTE = "no_pendiente";

}
