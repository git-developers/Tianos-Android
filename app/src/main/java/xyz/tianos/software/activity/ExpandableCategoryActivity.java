package xyz.tianos.software.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
//import com.example.expandablerecyclerview.R;

import xyz.tianos.software.controller.CategoryController;
import xyz.tianos.software.entity.Category;
import xyz.tianos.software.expandable.ExpandableUtil;
import xyz.tianos.software.adapter.ExpandableCategoryAdapter;
import xyz.tianos.software.expandable.listeners.ListItemListener;
import xyz.tianos.software.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ExpandableCategoryActivity extends BaseActivity implements ListItemListener {

    CardView cvContainer;
    ArrayList<Category> listObject;
    ExpandableCategoryAdapter placeArrayAdapter;
    RecyclerView rvList;

    @NonNull
    protected CategoryController categoryController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_category);
        toolBar("Categorias", R.string.app_name);

        initialize();
        populateList();
        onClickListener();
    }

    private void initialize() {

        categoryController = new CategoryController(this);
        cvContainer = (CardView) findViewById(R.id.card_background);

        rvList = (RecyclerView) findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setItemAnimator(new DefaultItemAnimator());
    }

    private void populateList() {

        listObject = categoryController.findAll("username");

//        listObject = new ArrayList<>();
//
//        for (int i=0; i<6; i++) {
//            Category object = new Category();
//            object.setName("POLLO-" + i);
//            listObject.add(object);
//        }

        placeArrayAdapter = new ExpandableCategoryAdapter(ExpandableCategoryActivity.this, listObject);

        rvList.setAdapter(placeArrayAdapter);
    }

    private void onClickListener()
    {
        placeArrayAdapter.setListItemListener(this);

        rvList.post(new Runnable() {
            @Override
            public void run() {

                int baseHeight =  Math.round(50);
//                int baseHeight =  Math.round(getResources().getDimension(R.dimen.place_item_height));
                int collapseAmount = baseHeight * (listObject.size() - 1);
                ExpandableUtil.collapseWithNoAnimation(cvContainer, collapseAmount);
            }
        });
    }

    @Override
    public void onExpand() {
//        int from =  Math.round(getResources().getDimension(R.dimen.place_item_height));
        int from =  Math.round(50);
        int to = from * listObject.size();
        ExpandableUtil.expand(cvContainer, from, to);
    }

    @Override
    public void onCollapse() {
        int baseHeight =  Math.round(50);
//        int baseHeight =  Math.round(getResources().getDimension(R.dimen.place_item_height));
        int collapseAmount = baseHeight * (listObject.size() - 1);
        ExpandableUtil.collapse(cvContainer, collapseAmount);
    }
}