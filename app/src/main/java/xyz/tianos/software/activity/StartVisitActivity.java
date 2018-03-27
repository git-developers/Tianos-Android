package xyz.tianos.software.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import xyz.tianos.software.activity.startVisit.PagerAdapter;
import xyz.tianos.software.controller.PointOfSaleController;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Const;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * This activity demonstrates how retrofit and rx work together.
 */
public class StartVisitActivity extends BaseActivity {

    private static final String TAG = StartVisitActivity.class.getSimpleName();

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private TabLayout tabLayout;

    private ViewPager viewPager;

    @NonNull
    protected PointOfSaleController pointOfSaleController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_visit);
        toolBar("Inicio de visita", R.string.app_name);
        setSharePreferencesActivity(Const.ACTIVITY_START_VISIT);

        initialize();
        onClickListener();
    }

    private void initialize()
    {

        tabLayout = (TabLayout) findViewById(R.id.start_visit_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Visita"));
        tabLayout.addTab(tabLayout.newTab().setText("Pdv info"));
        tabLayout.addTab(tabLayout.newTab().setText("Pdv mapa"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pointOfSaleController = new PointOfSaleController(this);
        PointOfSale pointOfSale = pointOfSaleController.findOneById("1");

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(pointOfSale, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

    }

    private void onClickListener()
    {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void startVisit()
    {
        requestApiPointOfSale(); // Trigger our request and display afterwards
    }

    private void requestApiPointOfSale()
    {
//        mCompositeDisposable
//            .add(mPointOfSaleService.queryPointOfSale(44.1)
//            .subscribeOn(Schedulers.io()) // "work" on io thread
//            .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
//            .map(new Function<PointOfSaleResponse, List<PointOfSale>>() {
//                @Override
//                public List<PointOfSale> apply(@io.reactivex.annotations.NonNull
//                                               final PointOfSaleResponse response) throws Exception {
//                    // we want to have the geonames and not the wrapper object
//                    return response.point_of_sale;
//                }
//            })
//            .subscribe(new Consumer<List<PointOfSale>>() {
//                @Override
//                public void accept(@io.reactivex.annotations.NonNull
//                                   final List<PointOfSale> objects) throws Exception {
//
//                    if(objects != null) {
//
//                        pointOfSaleController.deleteTable();
//                        long idInserted = pointOfSaleController.insertList(username, objects);
//
//                        pbPointOfSale.setVisibility(View.GONE);
//                        ivPointOfSaleOk.setVisibility(View.VISIBLE);
//
//                        requestApiCategory();
//                    }
//                }
//            })
//        );
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onDestroy()
    {
        // DO NOT CALL .dispose()

        //LO BORRE POR MIENTRAS
//        mCompositeDisposable.clear();
        super.onDestroy();
    }

}
