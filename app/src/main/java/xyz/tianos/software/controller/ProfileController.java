package xyz.tianos.software.controller;

import android.content.Context;

import xyz.tianos.software.dao.implement.ProfileDaoImplement;
import xyz.tianos.software.entity.Profile;
import xyz.tianos.software.entity.User;

public class ProfileController extends BaseController {

    private static final String TAG = ProfileController.class.getSimpleName();
    private Context context;
    private ProfileDaoImplement dao;

    public ProfileController(Context context) {
        this.context = context;
        this.dao = new ProfileDaoImplement(this.context);
    }

    public long insertOnLogin(User user) {

        String username = user.getUsername();
        Profile profile = user.getProfile();

        long idInserted = dao.insert(username, profile);
        dao.closeDb();
        return idInserted;
    }

    public long insert(String username, Profile profile) {
        long idInserted = dao.insert(username, profile);
        dao.closeDb();
        return idInserted;
    }

    public Profile findOneByUsername(String username) {
        Profile object = dao.findOneByUsername(username);
        dao.closeDb();
        return object;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

}
