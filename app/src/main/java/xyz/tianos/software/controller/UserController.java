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

//    public HashMap wsLoginUser(String username, String password, String registrationId) {
//        HashMap<String, String> params = new HashMap<String, String>();
//        try {
//            params.put(Const.PARAMETER_USERNAME, username);
//            params.put(Const.PARAMETER_PASSWORD, password);
//            params.put(Const.PARAMETER_REGISTRATION_ID, registrationId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return params;
//    }
//
//    public HashMap wsParamUsername(String username) {
//        HashMap<String, String> params = new HashMap<String, String>();
//        try {
//            params.put(Const.PARAMETER_USERNAME, username);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return params;
//    }
//
//    public User parseJsonToObject(JSONObject jsonOutput) {
//
//        User object = null;
//
//        try {
//            if(jsonOutput.has(Const.JSON_KEY_USER)){
//                JSONObject jsonOutput2 = jsonOutput.getJSONObject(Const.JSON_KEY_USER);
//                Gson gson = Utils.gsonBuilder();
//                object = (User) gson.fromJson(jsonOutput2.toString(), User.class);
//            }
//        } catch (JSONException e) {
//            e.getMessage();
//        }
//
//        return object;
//    }

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

}
