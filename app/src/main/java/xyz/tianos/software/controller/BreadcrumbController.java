package xyz.tianos.software.controller;

import android.content.Context;

import java.util.ArrayList;

import xyz.tianos.software.dao.implement.BreadcrumbDaoImplement;
import xyz.tianos.software.entity.Breadcrumb;
import xyz.tianos.software.entity.Category;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.entity.User;

public class BreadcrumbController extends BaseController {

    private static final String TAG = BreadcrumbController.class.getSimpleName();
    private Context context;
    private BreadcrumbDaoImplement dao;

    public BreadcrumbController(Context context) {
        this.context = context;
        this.dao = new BreadcrumbDaoImplement(this.context);
    }

    public long save_1_User(User object) {
        long idInserted = dao.save_1_User(object);
        dao.closeDb();
        return idInserted;
    }

    public long save_2_PointOfSale(PointOfSale object) {
        long idInserted = dao.save_2_PointOfSale(object);
        dao.closeDb();
        return idInserted;
    }

    public long save_3_Category(Category object) {
        long idInserted = dao.save_3_Category(object);
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
