package xyz.elcomercio.software.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import xyz.elcomercio.software.dao.implement.PdvHasProductDaoImplement;
import xyz.elcomercio.software.entity.ListPdvHasProduct;
import xyz.elcomercio.software.entity.PdvHasProduct;
import xyz.elcomercio.software.entity.PointOfSale;
import xyz.elcomercio.software.entity.Visit;

public class PdvHasProductController extends BaseController {

    private static final String TAG = PdvHasProductController.class.getSimpleName();
    private Context context;
    private PdvHasProductDaoImplement dao;

    public PdvHasProductController(Context context) {
        this.context = context;
        this.dao = new PdvHasProductDaoImplement(this.context);
    }

    public long insert(PdvHasProduct object) {
        long idInserted = dao.insert(object);
        dao.closeDb();
        return idInserted;
    }

    public long insertList(List<PdvHasProduct> objects) {

        long idInserted = 0;

        for (final PdvHasProduct object : objects) {
            idInserted = dao.insert(object);
        }

        dao.closeDb();
        return idInserted;
    }

    public ArrayList<PdvHasProduct> findAll(String username) {
        ArrayList<PdvHasProduct> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public ArrayList<PdvHasProduct> findAllIdBackendNull(String username) {
        ArrayList<PdvHasProduct> objects = dao.findAllIdBackendNull(username);
        dao.closeDb();

        return objects;
    }

    public ListPdvHasProduct findAllList(String username) {
        ListPdvHasProduct objects = new ListPdvHasProduct();
        objects.setListPdvHasProduct(findAllIdBackendNull(username));

        return objects;
    }

    public PdvHasProduct findOneByUuid(String uuid) {
        PdvHasProduct object = dao.findOneByUuid(uuid);
        dao.closeDb();

        return object;
    }

    public long updateIdBackend(PdvHasProduct pdvHasProduct) {
        long object = dao.updateIdBackend(pdvHasProduct);
        dao.closeDb();

        return object;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
