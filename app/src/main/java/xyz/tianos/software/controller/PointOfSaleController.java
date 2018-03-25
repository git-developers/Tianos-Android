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

    public long insert(String username, PointOfSale object) {
        long idInserted = dao.insert(username, object);
        dao.closeDb();

        return idInserted;
    }

    /**
     *
     * @param username
     * @param objects
     * @return
     */
    public long insertList(String username, List<PointOfSale> objects) {

        long idInserted = 0;

        for (final PointOfSale object : objects) {
            idInserted = dao.insert(username, object);
        }

        dao.closeDb();
        return idInserted;
    }

    public ArrayList<PointOfSale> findAll(String username) {
        ArrayList<PointOfSale> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
