package xyz.elcomercio.software.controller;

import android.content.Context;

import java.util.ArrayList;

import xyz.elcomercio.software.dao.implement.BreadcrumbDaoImplement;
import xyz.elcomercio.software.entity.Breadcrumb;
import xyz.elcomercio.software.entity.Category;
import xyz.elcomercio.software.entity.PointOfSale;
import xyz.elcomercio.software.entity.User;

public class BreadcrumbController extends BaseController {

    private static final String TAG = BreadcrumbController.class.getSimpleName();
    private Context context;
    private BreadcrumbDaoImplement dao;

    public BreadcrumbController(Context context) {
        this.context = context;
        this.dao = new BreadcrumbDaoImplement(this.context);
    }

    public long insert(Breadcrumb object) {
        long idInserted = dao.insert(object);
        dao.closeDb();
        return idInserted;
    }

    public long update(Breadcrumb object) {
        long idInserted = dao.update(object);
        dao.closeDb();
        return idInserted;
    }

    public ArrayList<Breadcrumb> findAll(String username) {
        ArrayList<Breadcrumb> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public Breadcrumb findLast() {
        Breadcrumb object = dao.findLast();
        dao.closeDb();

        return object;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
