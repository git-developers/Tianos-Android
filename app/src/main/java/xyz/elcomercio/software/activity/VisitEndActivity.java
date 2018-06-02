package xyz.elcomercio.software.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import io.reactivex.disposables.CompositeDisposable;
import xyz.elcomercio.software.activity.visitEnd.PagerEndVisitAdapter;
import xyz.elcomercio.software.activity.visitStart.PagerStartVisitAdapter;
import xyz.elcomercio.software.controller.BreadcrumbController;
import xyz.elcomercio.software.entity.Breadcrumb;
import xyz.elcomercio.software.utils.Const;

/**
 * This activity demonstrates how retrofit and rx work together.
 */
public class VisitEndActivity extends BaseActivity {

    private static final String TAG = VisitEndActivity.class.getSimpleName();

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    protected Breadcrumb breadcrumb;
    private BreadcrumbController breadcrumbController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_visit);
        toolBar("Fin de visita", R.string.app_name);
        setSharePreferencesActivity(Const.ACTIVITY_VISIT_END);

        initialize();
        onClickListener();
    }

    private void initialize()
    {

        tabLayout = (TabLayout) findViewById(R.id.end_visit_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Visita"));
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        /**
         * viene desde activity:: point of sale
         */
        breadcrumbController = new BreadcrumbController(this);
        breadcrumb = breadcrumbController.findLast();

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerEndVisitAdapter adapter = new PagerEndVisitAdapter(breadcrumb.getPointOfSale(), getSupportFragmentManager(), tabLayout.getTabCount());
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

    @Override
    protected void onDestroy()
    {
        // DO NOT CALL .dispose()

        //LO BORRE POR MIENTRAS
//        mCompositeDisposable.clear();
        super.onDestroy();
    }

}
