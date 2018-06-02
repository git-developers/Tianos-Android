package xyz.tianos.software.controller;

import android.content.Context;

import xyz.tianos.software.dao.implement.UserDaoImplement;
import xyz.tianos.software.entity.User;
import xyz.tianos.software.utils.Const;
import xyz.tianos.software.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class UserController extends BaseController {

    private static final String TAG = UserController.class.getSimpleName();
    private Context context;
    private UserDaoImplement dao;

    public UserController(Context context) {
        this.context = context;
        this.dao = new UserDaoImplement(this.context);
    }

    public long insert(User user) {
        long idInserted = dao.insert(user);
        dao.closeDb();
        return idInserted;
    }

    public User findOneByUsername(String username) {
        User user = dao.findOneByUsername(username);
        dao.closeDb();
        return user;
    }

    public User findLastLogged() {
        User user = dao.findLastLogged();
        dao.closeDb();
        return user;
    }

    public void deleteTable() {
        dao.deleteTable();
        dao.closeDb();
    }

    public void deleteTableByUsername(String username) {
        dao.deleteTableByUsername(username);
        dao.closeDb();
    }

}
