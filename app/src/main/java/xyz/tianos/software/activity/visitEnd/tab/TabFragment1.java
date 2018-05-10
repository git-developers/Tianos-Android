package xyz.tianos.software.activity.visitEnd.tab;

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

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import xyz.tianos.software.activity.CategoryActivity;
import xyz.tianos.software.activity.R;
import xyz.tianos.software.controller.BreadcrumbController;
import xyz.tianos.software.controller.VisitController;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.entity.Visit;
import xyz.tianos.software.rxJava.Response.VisitResponse;
import xyz.tianos.software.rxJava.RetrofitHelper;
import xyz.tianos.software.rxJava.Service.VisitService;
import xyz.tianos.software.utils.Const;
import xyz.tianos.software.utils.Date;
import xyz.tianos.software.utils.Utils;

public class TabFragment1 extends Fragment {

    private static final String TAG = TabFragment1.class.getSimpleName();
    protected Button bNext;
    protected PointOfSale pointOfSale;

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private VisitService mVisitService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mCompositeDisposable = new CompositeDisposable();

        // Initialize the city endpoint
        mVisitService = new RetrofitHelper().getVisitService();

        View view = inflater.inflate(R.layout.start_visit_tab_1, container, false);

        pointOfSale = (PointOfSale) getArguments().getSerializable(Const.DATA_POINT_OF_SALE);

        TextView tab1PdvName = (TextView) view.findViewById(R.id.tab_pdv_name);
        tab1PdvName.setText(pointOfSale.getId() + " - " + pointOfSale.getName());

        TextView tab1PdvCode = (TextView) view.findViewById(R.id.tab_pdv_code);
        tab1PdvCode.setText(pointOfSale.getCode());

        bNext = (Button) view.findViewById(R.id.b_start_visit);
        bNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navigateToCategory();
                save_2_PointOfSale(pointOfSale);
                saveVisitStart();
//                requestApiStartVisit();
            }
        });

        return view;
    }

    private void save_2_PointOfSale(PointOfSale pointOfSale)
    {
        Visit object = new Visit();
        object.setUuid(Utils.getUuid());
        object.setVisitStart(Date.now());

        BreadcrumbController controller = new BreadcrumbController(getActivity());
        long idInserted = controller.save_2_PointOfSale(pointOfSale);
    }


    private void saveVisitStart()
    {
        Visit object = new Visit();
        object.setUuid(Utils.getUuid());
        object.setVisitStart(Date.now());

        VisitController controller = new VisitController(getActivity());
        long idInserted = controller.insert(object);
    }

    private void requestApiStartVisit()
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
            }
        });

        mCompositeDisposable
                .add(mVisitService.queryVisit(44.1)
                    .subscribeOn(Schedulers.io()) // "work" on io thread
                    .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
                    .map(new Function<VisitResponse, List<Visit>>() {
                        @Override
                        public List<Visit> apply(@io.reactivex.annotations.NonNull
                                                       final VisitResponse response) throws Exception {
                            // we want to have the geonames and not the wrapper object
                            return response.visit;
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
                    .subscribe(new Consumer<List<Visit>>() {

                        @Override
                        public void accept(@io.reactivex.annotations.NonNull final List<Visit> objects) throws Exception {

                            if(objects != null) {

                                Utils.shortToast(getActivity(), "Visita: se envio correctamente.");
                            }
                        }
                    })
                );
    }


    private void navigateToCategory()
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), CategoryActivity.class);
        startActivity(intent);
//        context.finish();
//        this.context.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}