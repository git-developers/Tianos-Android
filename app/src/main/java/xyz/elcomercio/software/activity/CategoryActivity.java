package xyz.elcomercio.software.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import xyz.elcomercio.software.controller.CategoryController;
import xyz.elcomercio.software.entity.Category;
import xyz.elcomercio.software.expandable.ExpandableUtil;
import xyz.elcomercio.software.adapter.CategoryAdapter;
import xyz.elcomercio.software.expandable.listeners.ListItemListener;

import java.util.ArrayList;

public class CategoryActivity extends BaseActivity implements ListItemListener {

    @NonNull
    CardView cvContainer;

    @NonNull
    ArrayList<Category> listObject;

    @NonNull
    CategoryAdapter placeArrayAdapter;

    @NonNull
    RecyclerView rvList;

    @NonNull
    protected CategoryController categoryController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
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

        placeArrayAdapter = new CategoryAdapter(CategoryActivity.this, listObject);

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