package xyz.tianos.software.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import java.util.List;

import xyz.tianos.software.adapter.PointOfSaleHasProductAdapter;
import xyz.tianos.software.controller.ProductController;
import xyz.tianos.software.entity.Product;
import xyz.tianos.software.utils.Const;

public class PointOfSaleHasProductActivity extends BaseActivity {

    private static final String TAG = PointOfSaleHasProductActivity.class.getSimpleName();
    private ListView listProduct;
    private ProductController productController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_of_sale_has_product);
        toolBar("Pdv -> Product", R.string.app_name);
        setSharePreferencesActivity(Const.ACTIVITY_PDV_HAS_PRODUCT);

        initialize();
        populateList();
    }

    private void initialize() {
        productController = new ProductController(PointOfSaleHasProductActivity.this);
        listProduct = (ListView) findViewById(R.id.listProduct);
    }

    private void populateList() {

        List<Product> listObject = productController.findAll("username");
        listProduct.setAdapter(new PointOfSaleHasProductAdapter(PointOfSaleHasProductActivity.this, listObject));

        /*
        if(listObject != null) {
            listProduct.setAdapter(new PointOfSaleAdapter(PointOfSaleHasProductActivity.this, listObject));
            listProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    Object o = listProduct.getItemAtPosition(position);
                    PointOfSale pointOfSale = (PointOfSale) o;

                    navigateToStartVisit();
                }
            });
        }
        */
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
