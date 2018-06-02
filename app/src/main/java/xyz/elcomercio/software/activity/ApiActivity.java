package xyz.elcomercio.software.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import xyz.elcomercio.software.controller.CategoryController;
import xyz.elcomercio.software.controller.PointOfSaleController;
import xyz.elcomercio.software.controller.ProductController;
import xyz.elcomercio.software.entity.ApiCategory;
import xyz.elcomercio.software.entity.PointOfSale;
import xyz.elcomercio.software.entity.Product;
import xyz.elcomercio.software.rxJava.Response.CategoryResponse;
import xyz.elcomercio.software.rxJava.Response.PointOfSaleResponse;
import xyz.elcomercio.software.rxJava.Response.ProductResponse;
import xyz.elcomercio.software.rxJava.RetrofitHelper;
import xyz.elcomercio.software.rxJava.Service.CategoryService;
import xyz.elcomercio.software.rxJava.Service.PointOfSaleService;
import xyz.elcomercio.software.rxJava.Service.ProductService;
import xyz.elcomercio.software.utils.Const;
import xyz.elcomercio.software.utils.Utils;

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
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);
        toolBar("Api", R.string.app_name);
        setSharePreferencesActivity(Const.ACTIVITY_API);

        initialize();
        onClickListener();
        requestApiPointOfSale(); // Trigger our request and display afterwards
    }

    private void initialize()
    {
        mCompositeDisposable = new CompositeDisposable();

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
                navigateToPointOfSale();
            }
        });
    }

    private void navigateToPointOfSale()
    {
        Intent intent = new Intent();
        intent.setClass(this, PointOfSaleActivity.class);
        startActivity(intent);
        ApiActivity.this.finish();

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void navigateToApi()
    {

        finish();
        startActivity(getIntent());

//        Intent intent = new Intent();
//        intent.setClass(this, ApiActivity.class);
//        startActivity(intent);
//        ApiActivity.this.finish();
//
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void requestApiPointOfSale()
    {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                try{
                    Log.e("GATAZO_LOGIN_XX", throwable.getClass().getName());             // io.reactivex.exceptions.OnErrorNotImplementedException
                    Log.e("GATAZO_LOGIN_XX", throwable.getCause().getClass().getName());  // java.lang.Exception
                    Log.e("GATAZO_LOGIN_XX", throwable.getMessage());                     // "Test"
                }catch (NullPointerException e){

                }

                throwable.printStackTrace();

                Utils.shortToast(ApiActivity.this, "Info PointOfSale: volver a intentar.");

                navigateToApi();
            }
        });

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
//            .doOnError(new Consumer<Throwable>() {
//                @Override
//                public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
//                    Utils.shortToast(ApiActivity.this, "Info pdv: volver a intentar.");
//                }
//            })

            .doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();

                    Log.d("ERROR_GATAZO", "SSSSSSSSSSSSSSSSS");
                }
            })
            .subscribe(new Consumer<List<PointOfSale>>() {

                @Override
                public void accept(@io.reactivex.annotations.NonNull final List<PointOfSale> objects) throws Exception {

                    if(objects != null) {

                        pointOfSaleController.deleteTable();
                        long idInserted = pointOfSaleController.insertList(usernameLastLogged, objects);

                        pbPointOfSale.setVisibility(View.GONE);
                        ivPointOfSaleOk.setVisibility(View.VISIBLE);

                        requestApiCategory();
                    } else {
                        Utils.shortToast(ApiActivity.this, "PDV: volver a intentar.");
                    }
                }
            })
        );
    }

    private void requestApiCategory()
    {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                try{
                    Log.e("GATAZO_LOGIN_XX", throwable.getClass().getName());             // io.reactivex.exceptions.OnErrorNotImplementedException
                    Log.e("GATAZO_LOGIN_XX", throwable.getCause().getClass().getName());  // java.lang.Exception
                    Log.e("GATAZO_LOGIN_XX", throwable.getMessage());                     // "Test"
                }catch (NullPointerException e){

                }

                throwable.printStackTrace();

                Utils.shortToast(ApiActivity.this, "Info Category: volver a intentar.");

                navigateToApi();
            }
        });

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
            .doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    Utils.shortToast(ApiActivity.this, "Info category: volver a intentar.");
                }
            })
            .subscribe(new Consumer<List<ApiCategory>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull
                                   final List<ApiCategory> objects) throws Exception {

//                    Log.d(TAG, "API ACTIVITY::object:: " + objects);

                    if(objects != null) {

                        categoryController.deleteTable();
                        long idInserted = categoryController.insertList(usernameLastLogged, objects, 0);

                        pbCategory.setVisibility(View.GONE);
                        ivCategoryOk.setVisibility(View.VISIBLE);

                        requestApiProduct();
                    } else {
                        Utils.shortToast(ApiActivity.this, "Category: volver a intentar.");
                    }
                }
            })
        );
    }

    private void requestApiProduct()
    {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                try{
                    Log.e("GATAZO_LOGIN_XX", throwable.getClass().getName());             // io.reactivex.exceptions.OnErrorNotImplementedException
                    Log.e("GATAZO_LOGIN_XX", throwable.getCause().getClass().getName());  // java.lang.Exception
                    Log.e("GATAZO_LOGIN_XX", throwable.getMessage());                     // "Test"
                }catch (NullPointerException e){

                }

                throwable.printStackTrace();

                Utils.shortToast(ApiActivity.this, "Info Product: volver a intentar.");

                navigateToApi();
            }
        });

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
            .doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
                    Utils.shortToast(ApiActivity.this, "Info producto: volver a intentar.");
                }
            })
            .subscribe(new Consumer<List<Product>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull
                                   final List<Product> objects) throws Exception {

                    if(objects != null) {

                        productController.deleteTable();
                        long idInserted = productController.insertList(usernameLastLogged, objects);

                        pbProduct.setVisibility(View.GONE);
                        ivProductOk.setVisibility(View.VISIBLE);

                        enableNextButton();
                    } else {
                        Utils.shortToast(ApiActivity.this, "Product: volver a intentar.");
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

}
