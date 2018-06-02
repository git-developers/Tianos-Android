package xyz.elcomercio.software.controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import xyz.elcomercio.software.dao.implement.CategoryDaoImplement;
import xyz.elcomercio.software.entity.ApiCategory;
import xyz.elcomercio.software.entity.Category;

public class CategoryController extends BaseController {

    private static final String TAG = CategoryController.class.getSimpleName();
    private Context context;
    private CategoryDaoImplement dao;

    public CategoryController(Context context) {
        this.context = context;
        this.dao = new CategoryDaoImplement(this.context);
    }

    public long insertList(String username, List<ApiCategory> objects, int parentId) {

        long idInserted = 0;

        for (final ApiCategory object : objects) {

            Category parent = object.getParent();
            List<ApiCategory> children = object.getChildren();

//            Log.d(TAG, "PARENT::::: CODE:: " + parent.getId() + "--NAME:: " + parent.getName());

            if(children.size() > 0) {
//                Log.d(TAG, "CHILDREN::::: :: " + parentId);

                insertList(username, children, parent.getId());
            }

            parent.setCategoryId(parentId);
            idInserted = dao.insert(username, parent);
        }

        dao.closeDb();
        return idInserted;
    }

    public ArrayList<Category> findAll(String username) {
        ArrayList<Category> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }
}
