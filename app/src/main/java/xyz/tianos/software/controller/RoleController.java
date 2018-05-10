package xyz.tianos.software.controller;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import xyz.tianos.software.dao.implement.ProfileDaoImplement;
import xyz.tianos.software.dao.implement.RoleDaoImplement;
import xyz.tianos.software.entity.Profile;
import xyz.tianos.software.entity.Role;
import xyz.tianos.software.entity.User;

public class RoleController extends BaseController {

    private static final String TAG = RoleController.class.getSimpleName();
    private Context context;
    private RoleDaoImplement dao;

    public RoleController(Context context) {
        this.context = context;
        this.dao = new RoleDaoImplement(this.context);
    }

    public long insertOnLogin(long idProfile, User user) {

        if(idProfile <= 0){
            return -1;
        }

        String username = user.getUsername();
        ArrayList<Role> roles = user.getProfile().getRole();

        long idInserted = 0;

        for (Role role : roles) {
            idInserted = dao.insert(username, idProfile, role);
        }


        dao.closeDb();
        return idInserted;
    }

    public long insert(String username, long idProfile, Role role) {
        long idInserted = dao.insert(username, idProfile, role);
        dao.closeDb();
        return idInserted;
    }

    public Role findOneByUsername(String username) {
        Role object = dao.findOneByUsername(username);
        dao.closeDb();
        return object;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
