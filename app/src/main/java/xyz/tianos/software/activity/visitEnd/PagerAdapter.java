package xyz.tianos.software.activity.visitEnd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import xyz.tianos.software.activity.visitEnd.tab.TabFragment1;
import xyz.tianos.software.activity.visitEnd.tab.TabFragment2;
import xyz.tianos.software.activity.visitEnd.tab.TabFragment3;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Const;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    private Bundle bundlePointOfSale;

    public PagerAdapter(PointOfSale pointOfSale, FragmentManager fm, int NumOfTabs) {
        super(fm);

        this.mNumOfTabs = NumOfTabs;

        bundlePointOfSale = new Bundle();
        bundlePointOfSale.putSerializable(Const.DATA_POINT_OF_SALE, pointOfSale);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:

                TabFragment1 tab1 = new TabFragment1();
                tab1.setArguments(bundlePointOfSale);

                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                tab2.setArguments(bundlePointOfSale);

                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                tab3.setArguments(bundlePointOfSale);

                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}