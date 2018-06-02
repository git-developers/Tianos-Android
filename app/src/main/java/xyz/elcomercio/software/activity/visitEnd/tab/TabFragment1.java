package xyz.elcomercio.software.activity.visitEnd.tab;

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
import xyz.elcomercio.software.activity.PointOfSaleActivity;
import xyz.elcomercio.software.activity.R;
import xyz.elcomercio.software.controller.BreadcrumbController;
import xyz.elcomercio.software.controller.VisitController;
import xyz.elcomercio.software.entity.Breadcrumb;
import xyz.elcomercio.software.entity.ListVisit;
import xyz.elcomercio.software.entity.PointOfSale;
import xyz.elcomercio.software.entity.User;
import xyz.elcomercio.software.entity.Visit;
import xyz.elcomercio.software.rxJava.Response.VisitEndResponse;
import xyz.elcomercio.software.rxJava.RetrofitHelper;
import xyz.elcomercio.software.rxJava.Service.VisitEndService;
import xyz.elcomercio.software.utils.Date;
import xyz.elcomercio.software.utils.Utils;

public class TabFragment1 extends Fragment {

    private static final String TAG = TabFragment1.class.getSimpleName();
    protected Button bNext;
    protected BreadcrumbController breadcrumbController;
    protected VisitController visitController;

    protected User userLastLogged;
    protected PointOfSale pointOfSale;
    protected Visit visit;
    protected Breadcrumb breadcrumb;

    /**
     * Collects all subscriptions to unsubscribe later
     */
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    @NonNull
    private VisitEndService mVisitService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        visitController = new VisitController(getActivity());
        breadcrumbController = new BreadcrumbController(getActivity());
        breadcrumb = breadcrumbController.findLast();

        View view = inflater.inflate(R.layout.end_visit_tab_1, container, false);

        TextView tab1PdvName = (TextView) view.findViewById(R.id.tab_pdv_name);
        tab1PdvName.setText(breadcrumb.getPointOfSale().getId() + " - " + breadcrumb.getPointOfSale().getName());

        TextView tab1PdvCode = (TextView) view.findViewById(R.id.tab_pdv_code);
        tab1PdvCode.setText(breadcrumb.getPointOfSale().getCode());

        bNext = (Button) view.findViewById(R.id.b_end_visit);
        bNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                saveVisitEnd();
                requestApiVisitEnd();
                navigateToPointOfSale();

            }
        });

        return view;
    }


    private void saveVisitEnd()
    {

        Visit object = visitController.findLast();
        object.setVisitEnd(Date.now());

        long idInserted = visitController.updateVisitEnd(object);
    }

    private void requestApiVisitEnd()
    {

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                try{
                    Log.e("GATAZO_VISIT_START_XX", throwable.getClass().getName());             // io.reactivex.exceptions.OnErrorNotImplementedException
                    Log.e("GATAZO_VISIT_START_XX", throwable.getCause().getClass().getName());  // java.lang.Exception
                    Log.e("GATAZO_VISIT_START_XX", throwable.getMessage());                     // "Test"
                }catch (NullPointerException e){

                }

                throwable.printStackTrace();
            }
        });

        ListVisit listVisit = visitController.findAllListEnd(breadcrumb.getUsername());

//        for (Visit visit : listVisit.getListVisit()) {
//            Log.d("VISITAS_ENVIADAS", "ID:::: " + visit.getId());
//        }

        mCompositeDisposable = new CompositeDisposable();
        mVisitService = new RetrofitHelper().getVisitEndService();

        mCompositeDisposable
            .add(mVisitService.queryVisit(listVisit)
                .subscribeOn(Schedulers.io()) // "work" on io thread
                .observeOn(AndroidSchedulers.mainThread()) // "listen" on UIThread
                .map(new Function<VisitEndResponse, List<Visit>>() {
                    @Override
                    public List<Visit> apply(@io.reactivex.annotations.NonNull final VisitEndResponse response) throws Exception {
                        // we want to have the geonames and not the wrapper object
                        return response.visits;
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();

                        Log.d("ERROR_RETROFIT", "SSSSS");
                    }
                })
                .subscribe(new Consumer<List<Visit>>() {

                    @Override
                    public void accept(@io.reactivex.annotations.NonNull final List<Visit> objects) throws Exception {

                        if(objects != null) {

                            for (Visit visit : objects){

                                Visit sqlite = visitController.findOneByUuid(visit.getUuid());
                                sqlite.setIdBackend(visit.getIdBackend());
                                visitController.updateIdBackendEnd(sqlite);
                            }

                            Utils.shortToast(getActivity(), "Visita: se envio correctamente.");
                        }
                    }
                })
            );
    }

    private void navigateToPointOfSale()
    {
        Intent intent = new Intent();
        intent.setClass(getActivity(), PointOfSaleActivity.class);
        startActivity(intent);
//        context.finish();
//        this.context.overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}