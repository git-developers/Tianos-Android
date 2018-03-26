package xyz.tianos.software.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import xyz.tianos.software.adapter.PointOfSaleAdapter;
import xyz.tianos.software.controller.PointOfSaleController;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Utils;

public class PointOfSaleHasProductActivity extends BaseActivity {

    private static final String TAG = PointOfSaleHasProductActivity.class.getSimpleName();
    private ListView listView;
    private PointOfSaleController pointOfSaleController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_of_sale);
        toolBar("Puntos de venta", R.string.app_name);

        initialize();
        populateList();
    }

    private void initialize() {
        pointOfSaleController = new PointOfSaleController(PointOfSaleHasProductActivity.this);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void populateList() {

        List<PointOfSale> listObject = pointOfSaleController.findAll("username");

        if(listObject != null) {
            listView.setAdapter(new PointOfSaleAdapter(PointOfSaleHasProductActivity.this, listObject));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = listView.getItemAtPosition(position);
                    PointOfSale pointOfSale = (PointOfSale) o;
                    Utils.shortToast(PointOfSaleHasProductActivity.this, "Seleccionado: " + pointOfSale.getName());
                }
            });
        }
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

//    private void gotoCoursesByUser() {
//        Intent intent = new Intent();
////        intent.setClass(PointOfSaleActivity.this, CoursesByUserActivity.class);
//        startActivity(intent);
////        CoursesActivity.this.finish();
//
//        /* Apply our act_1_splash exit (fade out) and menu_reports entry (fade in) animation transitions. */
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
//    }


    /*
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

                        listView.setAdapter(new PointOfSaleAdapter(PointOfSaleActivity.this, listObject));

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

//                                Utils.shortToast(PointOfSaleActivity.this, "Seleccionado:" + " " + user.getName());
                                Utils.shortToast(PointOfSaleActivity.this, "Seleccionado: POS");
                            }
                        });
                    }else{
                        Utils.shortToast(PointOfSaleActivity.this, "NULL listObject");
                    }
                }else{
                    Utils.shortToast(PointOfSaleActivity.this, response.getMessage());
                }
            }
        });

    }
    */

}
