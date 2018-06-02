package xyz.elcomercio.software.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import xyz.elcomercio.software.adapter.PointOfSaleHasProductAdapter;
import xyz.elcomercio.software.controller.BreadcrumbController;
import xyz.elcomercio.software.controller.PdvHasProductController;
import xyz.elcomercio.software.controller.ProductController;
import xyz.elcomercio.software.entity.Breadcrumb;
import xyz.elcomercio.software.entity.ListPdvHasProduct;
import xyz.elcomercio.software.entity.PdvHasProduct;
import xyz.elcomercio.software.entity.Product;
import xyz.elcomercio.software.rxJava.Response.PdvHasProductResponse;
import xyz.elcomercio.software.rxJava.RetrofitHelper;
import xyz.elcomercio.software.rxJava.Service.PdvHasProductService;
import xyz.elcomercio.software.utils.Const;
import xyz.elcomercio.software.utils.Utils;

public class PointOfSaleHasProductActivity extends BaseActivity {

    private static final String TAG = PointOfSaleHasProductActivity.class.getSimpleName();
    private ListView listProduct;
    private TextView tableTvPdvName, tableTvCategoryName;
    protected Button bSendData;
    protected Breadcrumb breadcrumb;
    protected PointOfSaleHasProductAdapter pointOfSaleHasProductAdapter;

    private PdvHasProductController pdvHasProductController;
    private BreadcrumbController breadcrumbController;
    private ProductController productController;

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private PdvHasProductService mPdvHasProductService;

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
        productController = new ProductController(this);
        breadcrumbController = new BreadcrumbController(this);
        pdvHasProductController = new PdvHasProductController(this);

        tableTvPdvName = (TextView) findViewById(R.id.tableTv_pdv_name);
        tableTvCategoryName = (TextView) findViewById(R.id.tableTv_category_name);
        listProduct = (ListView) findViewById(R.id.listProduct);

        breadcrumb = breadcrumbController.findLast();

        tableTvPdvName.setText("(" + breadcrumb.getPointOfSale().getId() + ")" + breadcrumb.getPointOfSale().getName());
        tableTvCategoryName.setText("(*) categoria");

//        REGISTRAR PRODUCTOS
        bSendData = (Button) findViewById(R.id.b_send_data);
        bSendData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                savePdvHasProduct();

                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                requestPdvHasProduct();

                navigateToVisitEnd();

            }
        });

    }

    private void populateList() {
        List<Product> listObject = productController.findAll("username");
        pointOfSaleHasProductAdapter = new PointOfSaleHasProductAdapter(PointOfSaleHasProductActivity.this, listObject);
        listProduct.setAdapter(pointOfSaleHasProductAdapter);
    }

    private void savePdvHasProduct() {

        List<PdvHasProduct> objects = new ArrayList<PdvHasProduct>();

        for (int i = 0; i < pointOfSaleHasProductAdapter.getCount(); i++){

            Product product = (Product) pointOfSaleHasProductAdapter.getItem(i);

            if(product.getCantidadProduct() <= 0){
                continue;
            }

            PdvHasProduct object = new PdvHasProduct();
            object.setUsername(usernameLastLogged);
            object.setPointOfSaleId(breadcrumb.getPointOfSale().getId());
            object.setProductId(product.getId());
            object.setQuantity(product.getCantidadProduct());
            objects.add(object);
        }

        pdvHasProductController.insertList(objects);
    }

    private void requestPdvHasProduct()
    {

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                try{
                    Log.e("GATAZO_VISIT_START_XX", throwable.getClass().getName());             // io.reactivex.exceptions.OnErrorNotImplementedException
                    Log.e("GATAZO_VISIT_START_XX", throwable.getCause().getClass().getName());  // java.lang.Exception
                    Log.e("GATAZO_VISIT_START_XX", throwable.getMessage());                     // "Test"
                }catch (NullPointerException e){

                }

                throwable.printStackTrace();
            }
        });

        ListPdvHasProduct listPdvHasProduct = pdvHasProductController.findAllList(userLastLogged.getUsername());

        for (PdvHasProduct visit : listPdvHasProduct.getListPdvHasProduct()) {
            Log.d("PDV_PRODUCTOS_ID", "ID:::: " + visit.getId());
        }

        mCompositeDisposable = new CompositeDisposable();
        mPdvHasProductService = new RetrofitHelper().getPdvHasProductService();

        mCompositeDisposable
            .add(mPdvHasProductService.queryVisit(listPdvHasProduct)
            .subscribeOn(Schedulers.io()) // "work" on io thread
            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
            .map(new Function<PdvHasProductResponse, List<PdvHasProduct>>() {
                @Override
                public List<PdvHasProduct> apply(@io.reactivex.annotations.NonNull final PdvHasProductResponse response) throws Exception {
                    // we want to have the geonames and not the wrapper object
                    return response.pdvHasProduct;
                }
            })
            .doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();

                    Log.d("ERROR_RETROFIT", "SSSSS");
                }
            })
            .subscribe(new Consumer<List<PdvHasProduct>>() {

                @Override
                public void accept(@io.reactivex.annotations.NonNull final List<PdvHasProduct> objects) throws Exception {

                    if(objects != null) {

                        for (PdvHasProduct visit : objects){

                            PdvHasProduct sqlite = pdvHasProductController.findOneByUuid(visit.getUuid());
                            sqlite.setIdBackend(visit.getIdBackend());
                            pdvHasProductController.updateIdBackend(sqlite);
                        }

                        Utils.shortToast(PointOfSaleHasProductActivity.this, "Se registro correctamente.");
                    }
                }
            })
        );
    }

    private void navigateToVisitEnd()
    {

        Intent intent = new Intent();
        intent.setClass(this, VisitEndActivity.class);
//        intent.putExtra(Const.DATA_POINT_OF_SALE, pointOfSale);
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
