package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import xyz.tianos.software.activity.implement.IBase;
import xyz.tianos.software.adapter.PointOfSaleAdapter;
import xyz.tianos.software.controller.PointOfSaleController;
import xyz.tianos.software.controller.UserController;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.entity.WsResponse;
import xyz.tianos.software.utils.Const;
import xyz.tianos.software.utils.Utils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class ListPointOfSaleActivity extends BaseActivity implements IBase {

    private static final String TAG = "ListPointOfSaleActivity";
    private ListView listView;
    private UserController userController;
    private PointOfSaleController pointOfSaleController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);
        toolBar("Puntos de venta", R.string.app_name);

        initialize();
        populateList();
    }

    private void initialize() {
        userController = new UserController(this);
        pointOfSaleController = new PointOfSaleController(this);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void populateList() {
        HashMap paramsInput = userController.wsParamUsername(username);
        webServiceTask(Const.ACTIVITY_LIST_POINT_OF_SALE, ListPointOfSaleActivity.this, Const.ROUTE_LIST_POINT_OF_SALE, paramsInput);
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

    private void gotoCoursesByUser() {
        Intent intent = new Intent();
//        intent.setClass(ListPointOfSaleActivity.this, CoursesByUserActivity.class);
        startActivity(intent);
//        CoursesActivity.this.finish();

        /* Apply our act_1_splash exit (fade out) and menu_reports entry (fade in) animation transitions. */
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void handleOnResponse(final JSONObject jsonOutput) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                WsResponse response = pointOfSaleController.parseWsResponse(jsonOutput);
                int status = response.getStatus();

                if(status == Const.STATUS_SUCCESS){
                    List<PointOfSale> listObject = pointOfSaleController.parseJsonToArrayObject(jsonOutput);

                    if(listObject != null){


                        /*
                        listView.setAdapter(new PointOfSaleAdapter(ListPointOfSaleActivity.this, listObject));

                        // When the user clicks on the ListItem
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> a, View v, int position, long id) {


                                //TODO JAFETH
//                                Object o = listView.getItemAtPosition(position);
//                                User user = (User) o;
//
//                                long idInserted = userController.insert(user);
//
//                                gotoCoursesByUser();

//                                Utils.shortToast(ListPointOfSaleActivity.this, "Seleccionado:" + " " + user.getName());
                                Utils.shortToast(ListPointOfSaleActivity.this, "Seleccionado: POS");
                            }
                        });
                        */
                    }else{
                        Utils.shortToast(ListPointOfSaleActivity.this, "NULL listObject");
                    }
                }else{
                    Utils.shortToast(ListPointOfSaleActivity.this, response.getMessage());
                }
            }
        });

    }

}
