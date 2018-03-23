package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xyz.tianos.software.controller.PointOfSaleController;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.rxJava.Response.PointOfSaleResponse;
import xyz.tianos.software.rxJava.Service.PointOfSaleService;
import xyz.tianos.software.rxJava.RetrofitHelper;

/**
 * This activity demonstrates how retrofit and rx work together.
 */
public class ApiActivity extends BaseActivity {

    private static final String TAG = ApiActivity.class.getSimpleName();

    /**
     * We will query geonames with this service
     */
    @NonNull
    private PointOfSaleService mPointOfSaleService;

    @NonNull
    protected PointOfSaleController pointOfSaleController;

    @NonNull
    protected ProgressBar pbPointOfSale;

    @NonNull
    protected ImageView ivPointOfSaleOk;

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
        mPointOfSaleService = new RetrofitHelper().getPointOfSaleService();
        pointOfSaleController = new PointOfSaleController(this);
        pbPointOfSale = (ProgressBar) findViewById(R.id.pb_point_of_sale);
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
        intent.setClass(this, PointOfSaleActivity.class);
        startActivity(intent);
        ApiActivity.this.finish();

        /* Apply our act_1_splash exit (fade out) and menu_reports entry (fade in) animation transitions. */
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    protected void onDestroy()
    {
        // DO NOT CALL .dispose()
        mCompositeDisposable.clear();
        super.onDestroy();
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
                                               final PointOfSaleResponse pointOfSaleResponse) throws Exception {
                    // we want to have the geonames and not the wrapper object
                    return pointOfSaleResponse.point_of_sale;
                }
            })
            .subscribe(new Consumer<List<PointOfSale>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull
                                   final List<PointOfSale> pointOfSales) throws Exception {

                    if(pointOfSales != null) {
                        long idInserted = pointOfSaleController.insertList(username, pointOfSales);

                        pbPointOfSale.setVisibility(View.GONE);
                        ivPointOfSaleOk.setVisibility(View.VISIBLE);

                        requestApiProduct();
                    }
                }
            })
        );
    }

    private void requestApiProduct()
    {
        mCompositeDisposable
            .add(mPointOfSaleService.queryPointOfSale(44.1)
            .subscribeOn(Schedulers.io()) // "work" on io thread
            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
            .map(new Function<PointOfSaleResponse, List<PointOfSale>>() {
                @Override
                public List<PointOfSale> apply(@io.reactivex.annotations.NonNull
                                               final PointOfSaleResponse pointOfSaleResponse) throws Exception {
                    // we want to have the geonames and not the wrapper object
                    return pointOfSaleResponse.point_of_sale;
                }
            })
            .subscribe(new Consumer<List<PointOfSale>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull
                                   final List<PointOfSale> pointOfSales) throws Exception {

                    if(pointOfSales != null) {
                        long idInserted = pointOfSaleController.insertList(username, pointOfSales);

                        pbPointOfSale.setVisibility(View.GONE);
                        ivPointOfSaleOk.setVisibility(View.VISIBLE);

                    }
                }
            })
        );
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
