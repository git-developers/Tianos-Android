package xyz.tianos.software.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import xyz.tianos.software.activity.visitStart.PagerAdapter;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Const;

/**
 * This activity demonstrates how retrofit and rx work together.
 */
public class VisitStartActivity extends BaseActivity {

    private static final String TAG = VisitStartActivity.class.getSimpleName();

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    private TabLayout tabLayout;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_visit);
        toolBar("Inicio de visita", R.string.app_name);
        setSharePreferencesActivity(Const.ACTIVITY_VISIT_START);

        initialize();
        onClickListener();
    }

    private void initialize()
    {

        tabLayout = (TabLayout) findViewById(R.id.start_visit_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Visita"));
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Mapa"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        /**
         * viene desde activity:: point of sale
         */
        PointOfSale pointOfSale = (PointOfSale) getIntent().getSerializableExtra(Const.DATA_POINT_OF_SALE);

        Log.d("POLLO", "pointOfSale 22:: " + pointOfSale.getId());


        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(userLastLogged, pointOfSale, getSupportFragmentManager(), tabLayout.getTabCount());
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
