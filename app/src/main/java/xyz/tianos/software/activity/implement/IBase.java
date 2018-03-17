package xyz.tianos.software.activity.implement;

import xyz.tianos.software.entity.User;

import org.json.JSONObject;

import java.util.ArrayList;

public interface IBase {
    public void handleOnResponse(final JSONObject jsonOutput);
}
