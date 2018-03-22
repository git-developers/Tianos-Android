package xyz.tianos.software.controller;

import android.content.Context;

import xyz.tianos.software.dao.implement.PointOfSaleDaoImplement;
import xyz.tianos.software.entity.PointOfSale;

import java.util.ArrayList;
import java.util.List;

public class PointOfSaleController extends BaseController {

    private static final String TAG = PointOfSaleController.class.getSimpleName();
    private Context context;
    private PointOfSaleDaoImplement dao;

    public PointOfSaleController(Context context) {
        this.context = context;
        this.dao = new PointOfSaleDaoImplement(this.context);
    }

//    public HashMap wsListCourses(String username) {
//
//        HashMap<String, String> params = new HashMap<String, String>();
//        try {
//            params.put(Const.PARAMETER_USERNAME, username);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return params;
//    }

//    public List<PointOfSale> parseJsonToArrayObject(JSONObject jsonOutput) {
//
//        List<PointOfSale> listObject = null;
//
//        try {
//            if(jsonOutput.has(Const.JSON_KEY_POINT_OF_SALE)){
//                JSONArray jsonOutput2 = jsonOutput.getJSONArray(Const.JSON_KEY_POINT_OF_SALE);
//                Gson gson = Utils.gsonBuilder();
//                Type listType = new TypeToken<List<PointOfSale>>(){}.getType();
//                listObject = (List<PointOfSale>) gson.fromJson(jsonOutput2.toString(), listType);
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }
//
//        return listObject;
//    }

//    public PointOfSale parseJsonToObject(JSONObject jsonOutput) {
//
//        PointOfSale object = null;
//
//        try {
//            if(jsonOutput.has(Const.JSON_KEY_POINT_OF_SALE)){
//                JSONObject jsonOutput2 = jsonOutput.getJSONObject(Const.JSON_KEY_POINT_OF_SALE);
//                Gson gson = Utils.gsonBuilder();
//                object = (PointOfSale) gson.fromJson(jsonOutput2.toString(), PointOfSale.class);
//            }
//        } catch (JSONException e) {
//            e.getMessage();
//        }
//
//        return object;
//    }

    public long insert(String username, PointOfSale pointOfSale) {
        long idInserted = dao.insert(username, pointOfSale);
        dao.closeDb();
        return idInserted;
    }

    /**
     *
     * @param username
     * @param pointOfSales
     * @return
     */
    public long insertList(String username, List<PointOfSale> pointOfSales) {

        long idInserted = 0;

        for (final PointOfSale pointOfSale : pointOfSales) {
            idInserted = dao.insert(username, pointOfSale);
        }

        dao.closeDb();
        return idInserted;
    }

    public ArrayList<PointOfSale> findAll(String username) {
        ArrayList<PointOfSale> pointOfSales = dao.findAll(username);
        dao.closeDb();
        return pointOfSales;
    }

}
