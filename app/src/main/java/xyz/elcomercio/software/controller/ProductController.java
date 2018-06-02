package xyz.elcomercio.software.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import xyz.elcomercio.software.dao.implement.ProductDaoImplement;
import xyz.elcomercio.software.entity.Product;

public class ProductController extends BaseController {

    private static final String TAG = ProductController.class.getSimpleName();
    private Context context;
    private ProductDaoImplement dao;

    public ProductController(Context context) {
        this.context = context;
        this.dao = new ProductDaoImplement(this.context);
    }

    public long insert(String username, Product object) {
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
    public long insertList(String username, List<Product> objects) {

        long idInserted = 0;

        for (final Product object : objects) {
            idInserted = dao.insert(username, object);
        }

        dao.closeDb();
        return idInserted;
    }

    public ArrayList<Product> findAll(String username) {
        ArrayList<Product> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
