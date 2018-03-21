package xyz.tianos.software.activity;

import android.os.Bundle;

import java.util.List;

import android.support.annotation.NonNull;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.rxJava.Response.CityResponse;
import xyz.tianos.software.rxJava.Response.PointOfSaleResponse;
import xyz.tianos.software.rxJava.Service.PointOfSaleService;
import xyz.tianos.software.entity.Geoname;
import xyz.tianos.software.rxJava.RetrofitHelper;

/**
 * This activity demonstrates how retrofit and rx work together.
 */
public class ApiActivity extends BaseActivity {

    /**
     * We will query geonames with this service
     */
    @NonNull
    private PointOfSaleService mPointOfSaleService;

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private TextView mOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api);
        toolBar("Api", R.string.app_name);

        mOutputTextView = (TextView) findViewById(R.id.output);

        // Initialize the city endpoint
        mPointOfSaleService = new RetrofitHelper().getPointOfSaleService();
//        mCityService = new RetrofitHelper().getCityService();

        // Trigger our request and display afterwards
        requestGeonames();
    }

    @Override
    protected void onDestroy() {
        // DO NOT CALL .dispose()
        mCompositeDisposable.clear();
        super.onDestroy();
    }

    private void requestGeonames() {
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
                public void accept(@io.reactivex.annotations.NonNull final List<PointOfSale> pointOfSales) throws Exception {
                    displayPointOfSales(pointOfSales);
                }
            })
        );
    }

    private void displayPointOfSales(@NonNull final List<PointOfSale> pointOfSales) {
        // Cheap way to display a list of Strings - I was too lazy to implement a RecyclerView
        final StringBuilder output = new StringBuilder();

        output.append(" --------- ").append("\n");

        if(pointOfSales != null){
            for (final PointOfSale pointOfSale : pointOfSales) {
                output.append(pointOfSale.getName()).append("\n");
            }
        }

        mOutputTextView.setText(output.toString());
    }

}
