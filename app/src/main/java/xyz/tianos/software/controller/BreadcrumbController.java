package xyz.tianos.software.controller;

import android.content.Context;

import java.util.ArrayList;

import xyz.tianos.software.dao.implement.BreadcrumbDaoImplement;
import xyz.tianos.software.entity.Breadcrumb;

public class BreadcrumbController extends BaseController {

    private static final String TAG = BreadcrumbController.class.getSimpleName();
    private Context context;
    private BreadcrumbDaoImplement dao;

    public BreadcrumbController(Context context) {
        this.context = context;
        this.dao = new BreadcrumbDaoImplement(this.context);
    }

    public long insert(String username, Breadcrumb object) {
        long idInserted = dao.insert(username, object);
        dao.closeDb();
        return idInserted;
    }

    public ArrayList<Breadcrumb> findAll(String username) {
        ArrayList<Breadcrumb> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
