package xyz.tianos.software.controller;

import android.content.Context;

import java.util.ArrayList;

import xyz.tianos.software.dao.implement.VisitDaoImplement;
import xyz.tianos.software.entity.Visit;

public class VisitController extends BaseController {

    private static final String TAG = VisitController.class.getSimpleName();
    private Context context;
    private VisitDaoImplement dao;

    public VisitController(Context context) {
        this.context = context;
        this.dao = new VisitDaoImplement(this.context);
    }

    public long insert(Visit object) {
        long idInserted = dao.insert(object);
        dao.closeDb();
        return idInserted;
    }

    public ArrayList<Visit> findAll(String username) {
        ArrayList<Visit> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
