package xyz.elcomercio.software.controller;

import android.app.Application;

import xyz.elcomercio.software.entity.User;
import xyz.elcomercio.software.entity.WsResponse;
import xyz.elcomercio.software.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

public class BaseController extends Application {

    public WsResponse parseWsResponse(JSONObject jsonOutput) {

        WsResponse object = null;

        try {
            Gson gson = Utils.gsonBuilder();
            object = (WsResponse) gson.fromJson(jsonOutput.toString(), WsResponse.class);
        } catch (Exception e) {
            e.getMessage();
        }

        return object;
    }

}
