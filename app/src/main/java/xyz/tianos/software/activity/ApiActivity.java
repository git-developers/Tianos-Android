package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import xyz.tianos.software.activity.implement.IBase;
import xyz.tianos.software.adapter.PointOfSaleAdapter;
import xyz.tianos.software.controller.PointOfSaleController;
import xyz.tianos.software.controller.UserController;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.entity.WsResponse;
import xyz.tianos.software.utils.Const;
import xyz.tianos.software.utils.Utils;

public class ApiActivity extends BaseActivity implements IBase {

    private static final String TAG = "ApiActivity";
    private ListView listView;
    private UserController userController;
    private PointOfSaleController pointOfSaleController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.father_list_children);
        toolBar("Api", R.string.app_name);

        initialize();
        populateList();
    }

    private void initialize() {
        userController = new UserController(this);
        pointOfSaleController = new PointOfSaleController(this);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void populateList() {
//        HashMap paramsInput = userController.wsParamUsername(username);
//        webServiceTask(Const.ACTIVITY_LIST_POINT_OF_SALE, ApiActivity.this, Const.ROUTE_LIST_POINT_OF_SALE, paramsInput);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void handleOnResponse(JSONObject jsonOutput) {

    }
}
