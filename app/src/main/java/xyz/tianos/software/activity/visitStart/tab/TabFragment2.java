package xyz.tianos.software.activity.visitStart.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xyz.tianos.software.activity.R;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Const;

public class TabFragment2 extends Fragment {

    private static final String TAG = TabFragment1.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.start_visit_tab_2, container, false);

        PointOfSale pointOfSale = (PointOfSale) getArguments().getSerializable(Const.DATA_POINT_OF_SALE);

        TextView pdvName = (TextView) view.findViewById(R.id.tab_pdv_name);
        pdvName.setText(pointOfSale.getId() + " - " + pointOfSale.getName());

        TextView pdvCode = (TextView) view.findViewById(R.id.tab_pdv_code);
        pdvCode.setText(pointOfSale.getCode());

        TextView tab1PdvName = (TextView) view.findViewById(R.id.tab_pdv_name);
        tab1PdvName.setText(pointOfSale.getId() + " - " + pointOfSale.getName());

        TextView pdvLatitude = (TextView) view.findViewById(R.id.tab_pdv_latitude);
        pdvLatitude.setText(pointOfSale.getLatitudeStr());

        TextView pdvLongitude = (TextView) view.findViewById(R.id.tab_pdv_longitude);
        pdvLongitude.setText(pointOfSale.getLongitudeStr());

        Log.d(TAG, "POLLO::: " + pointOfSale.getName());

        return view;
    }
}