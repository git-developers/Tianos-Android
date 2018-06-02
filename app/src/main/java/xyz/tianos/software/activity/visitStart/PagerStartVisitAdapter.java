package xyz.tianos.software.activity.visitStart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import xyz.tianos.software.activity.visitStart.tab.TabFragment1;
import xyz.tianos.software.activity.visitStart.tab.TabFragment2;
import xyz.tianos.software.activity.visitStart.tab.TabFragment3;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.entity.User;
import xyz.tianos.software.utils.Const;

public class PagerStartVisitAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    private Bundle bundle;

    public PagerStartVisitAdapter(User userLastLogged, PointOfSale pointOfSale, FragmentManager fm, int NumOfTabs) {
        super(fm);

        this.mNumOfTabs = NumOfTabs;

        bundle = new Bundle();
        bundle.putSerializable(Const.DATA_USER, userLastLogged);
        bundle.putSerializable(Const.DATA_POINT_OF_SALE, pointOfSale);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:

                TabFragment1 tab1 = new TabFragment1();
                tab1.setArguments(bundle);

                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                tab2.setArguments(bundle);

                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                tab3.setArguments(bundle);

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