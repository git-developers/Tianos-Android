package xyz.elcomercio.software.controller;

import android.content.Context;

import java.util.ArrayList;

import xyz.elcomercio.software.dao.implement.VisitDaoImplement;
import xyz.elcomercio.software.entity.ListVisit;
import xyz.elcomercio.software.entity.Visit;

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

    public long updateVisitEnd(Visit visit) {
        long object = dao.updateVisitEnd(visit);
        dao.closeDb();

        return object;
    }

    public ArrayList<Visit> findAll(String username) {
        ArrayList<Visit> objects = dao.findAll(username);
        dao.closeDb();

        return objects;
    }

    public ListVisit findAllListStart(String username) {
        ListVisit list = new ListVisit();

        ArrayList<Visit> objects = dao.findAllListStart(username);
        dao.closeDb();

        list.setListVisit(objects);

        return list;
    }


    public ListVisit findAllListEnd(String username) {
        ListVisit list = new ListVisit();

        ArrayList<Visit> objects = dao.findAllListEnd(username);
        dao.closeDb();

        list.setListVisit(objects);

        return list;
    }

    public Visit findOneByUuid(String uuid) {
        Visit object = dao.findOneByUuid(uuid);
        dao.closeDb();

        return object;
    }

    public long updateIdBackendStart(Visit visit) {
        long object = dao.updateIdBackendStart(visit);
        dao.closeDb();

        return object;
    }

    public long updateIdBackendEnd(Visit visit) {
        long object = dao.updateIdBackendEnd(visit);
        dao.closeDb();

        return object;
    }

    public Visit findLast() {
        Visit object = dao.findLast();
        dao.closeDb();

        return object;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
