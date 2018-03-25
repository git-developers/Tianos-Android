package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xyz.tianos.software.controller.CategoryController;
import xyz.tianos.software.entity.ApiCategory;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.entity.Product;
import xyz.tianos.software.rxJava.Response.CategoryResponse;
import xyz.tianos.software.rxJava.Response.ProductResponse;
import xyz.tianos.software.rxJava.RetrofitHelper;
import xyz.tianos.software.controller.PointOfSaleController;
import xyz.tianos.software.controller.ProductController;
import xyz.tianos.software.rxJava.Response.PointOfSaleResponse;
import xyz.tianos.software.rxJava.Service.CategoryService;
import xyz.tianos.software.rxJava.Service.PointOfSaleService;
import xyz.tianos.software.rxJava.Service.ProductService;

/**
 * This activity demonstrates how retrofit and rx work together.
 */
public class ApiActivity extends BaseActivity {

    private static final String TAG = ApiActivity.class.getSimpleName();

    @NonNull
    protected PointOfSaleController pointOfSaleController;

    @NonNull
    protected ProductController productController;

    @NonNull
    protected CategoryController categoryController;

    @NonNull
    private PointOfSaleService mPointOfSaleService;

    @NonNull
    private ProductService mProductService;

    @NonNull
    private CategoryService mCategoryService;

    @NonNull
    protected ProgressBar pbPointOfSale, pbCategory, pbProduct;

    @NonNull
    protected ImageView ivPointOfSaleOk, ivCategoryOk, ivProductOk;

    @NonNull
    protected Button bNext;

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);
        toolBar("Api", R.string.app_name);

        initialize();
        onClickListener();
        requestApiPointOfSale(); // Trigger our request and display afterwards
    }

    private void initialize()
    {
        // Initialize the city endpoint
        mProductService = new RetrofitHelper().getProductService();
        mCategoryService = new RetrofitHelper().getCategoryService();
        mPointOfSaleService = new RetrofitHelper().getPointOfSaleService();

        productController = new ProductController(this);
        categoryController = new CategoryController(this);
        pointOfSaleController = new PointOfSaleController(this);

        pbProduct = (ProgressBar) findViewById(R.id.pb_product);
        pbCategory = (ProgressBar) findViewById(R.id.pb_category);
        pbPointOfSale = (ProgressBar) findViewById(R.id.pb_point_of_sale);

        ivProductOk = (ImageView) findViewById(R.id.iv_product_ok);
        ivCategoryOk = (ImageView) findViewById(R.id.iv_category_ok);
        ivPointOfSaleOk = (ImageView) findViewById(R.id.iv_point_of_sale_ok);

        bNext = (Button) findViewById(R.id.b_next);
    }

    private void onClickListener()
    {
        bNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gotoPointOfSale();
            }
        });
    }

    private void gotoPointOfSale()
    {
        Intent intent = new Intent();
//        intent.setClass(this, PointOfSaleActivity.class);
        intent.setClass(this, CategoryActivity.class);
        startActivity(intent);
        ApiActivity.this.finish();

        /* Apply our act_1_splash exit (fade out) and menu_reports entry (fade in) animation transitions. */
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void requestApiPointOfSale()
    {
        mCompositeDisposable
            .add(mPointOfSaleService.queryPointOfSale(44.1)
            .subscribeOn(Schedulers.io()) // "work" on io thread
            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
            .map(new Function<PointOfSaleResponse, List<PointOfSale>>() {
                @Override
                public List<PointOfSale> apply(@io.reactivex.annotations.NonNull
                                               final PointOfSaleResponse response) throws Exception {
                    // we want to have the geonames and not the wrapper object
                    return response.point_of_sale;
                }
            })
            .subscribe(new Consumer<List<PointOfSale>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull
                                   final List<PointOfSale> objects) throws Exception {

                    if(objects != null) {

                        pointOfSaleController.deleteTable();
                        long idInserted = pointOfSaleController.insertList(username, objects);

                        pbPointOfSale.setVisibility(View.GONE);
                        ivPointOfSaleOk.setVisibility(View.VISIBLE);

                        requestApiCategory();
                    }
                }
            })
        );
    }

    private void requestApiCategory()
    {
        mCompositeDisposable
            .add(mCategoryService.queryCategory(44.1)
            .subscribeOn(Schedulers.io()) // "work" on io thread
            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
            .map(new Function<CategoryResponse, List<ApiCategory>>() {
                @Override
                public List<ApiCategory> apply(@io.reactivex.annotations.NonNull
                                               final CategoryResponse response) throws Exception {
                    // we want to have the geonames and not the wrapper object
                    return response.category;
                }
            })
            .subscribe(new Consumer<List<ApiCategory>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull
                                   final List<ApiCategory> objects) throws Exception {

//                    Log.d(TAG, "API ACTIVITY::object:: " + objects);

                    if(objects != null) {

                        categoryController.deleteTable();
                        long idInserted = categoryController.insertList(username, objects);

                        pbCategory.setVisibility(View.GONE);
                        ivCategoryOk.setVisibility(View.VISIBLE);

                        requestApiProduct();
                    }
                }
            })
        );
    }

    private void requestApiProduct()
    {
        mCompositeDisposable
            .add(mProductService.queryProduct(44.1)
            .subscribeOn(Schedulers.io()) // "work" on io thread
            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
            .map(new Function<ProductResponse, List<Product>>() {
                @Override
                public List<Product> apply(@io.reactivex.annotations.NonNull
                                               final ProductResponse response) throws Exception {
                    // we want to have the geonames and not the wrapper object
                    return response.product;
                }
            })
            .subscribe(new Consumer<List<Product>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull
                                   final List<Product> objects) throws Exception {

                    if(objects != null) {

                        productController.deleteTable();
                        long idInserted = productController.insertList(username, objects);

                        pbProduct.setVisibility(View.GONE);
                        ivProductOk.setVisibility(View.VISIBLE);

                        enableNextButton();
                    }
                }
            })
        );
    }

    private void enableNextButton()
    {
        bNext.setEnabled(true);
        bNext.setClickable(true);
        bNext.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_primary));
    }

    @Override
    protected void onDestroy()
    {
        // DO NOT CALL .dispose()
        mCompositeDisposable.clear();
        super.onDestroy();
    }

//    private void displayPointOfSales(@NonNull final List<PointOfSale> pointOfSales) {
//        // Cheap way to display a list of Strings - I was too lazy to implement a RecyclerView
//        final StringBuilder output = new StringBuilder();
//
//        output.append(" --------- ").append("\n");
//
//        if(pointOfSales != null){
//            for (final PointOfSale pointOfSale : pointOfSales) {
//                output.append(pointOfSale.getName()).append("\n");
//            }
//        }
//
//        mOutputTextView.setText(output.toString());
//    }

}
