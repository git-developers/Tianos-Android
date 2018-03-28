package xyz.tianos.software.activity.startVisit.tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import xyz.tianos.software.activity.CategoryActivity;
import xyz.tianos.software.activity.R;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Const;

public class TabFragment1 extends Fragment {

    private static final String TAG = TabFragment1.class.getSimpleName();

    private Context context;

    @NonNull
    protected Button bNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.context = getActivity();
        PointOfSale pointOfSale = (PointOfSale) getArguments().getSerializable(Const.DATA_POINT_OF_SALE);

        View view = inflater.inflate(R.layout.start_visit_tab_1, container, false);

        Log.d(TAG, "GATOO1111::: " + pointOfSale.getName());


        TextView tab1PdvName = (TextView) view.findViewById(R.id.tab1_pdv_name);
        tab1PdvName.setText("NOMBRE::: " + pointOfSale.getName());

        bNext = (Button) view.findViewById(R.id.b_start_visit);
        bNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToCategory();
            }
        });

        return view;
    }


    private void navigateToCategory()
    {
        Intent intent = new Intent();
        intent.setClass(context, CategoryActivity.class);
        startActivity(intent);
//        context.finish();
//        this.context.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}