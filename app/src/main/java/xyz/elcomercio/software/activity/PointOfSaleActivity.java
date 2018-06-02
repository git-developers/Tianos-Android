package xyz.elcomercio.software.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import xyz.elcomercio.software.adapter.PointOfSaleAdapter;
import xyz.elcomercio.software.controller.PointOfSaleController;
import xyz.elcomercio.software.entity.PointOfSale;
import xyz.elcomercio.software.utils.Const;

public class PointOfSaleActivity extends BaseActivity {

    private static final String TAG = PointOfSaleActivity.class.getSimpleName();
    private ListView listView;
    private PointOfSaleController pointOfSaleController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_of_sale);
        toolBar("Puntos de venta", R.string.app_name);
        setSharePreferencesActivity(Const.ACTIVITY_POINT_OF_SALE);

        initialize();
        populateList();
    }

    private void initialize() {
        pointOfSaleController = new PointOfSaleController(PointOfSaleActivity.this);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void populateList() {

        List<PointOfSale> listObject = pointOfSaleController.findAll("username");

        if(listObject != null) {
            listView.setAdapter(new PointOfSaleAdapter(PointOfSaleActivity.this, listObject));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = listView.getItemAtPosition(position);
                    PointOfSale pointOfSale = (PointOfSale) o;

                    navigateToVisitStart(pointOfSale);
                }
            });
        }
    }

    private void navigateToVisitStart(PointOfSale pointOfSale)
    {

        Intent intent = new Intent();
        intent.setClass(this, VisitStartActivity.class);
        intent.putExtra(Const.DATA_POINT_OF_SALE, pointOfSale);
        startActivity(intent);
//        PointOfSaleActivity.this.finish();

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
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

}
