package xyz.elcomercio.software.adapter;

import xyz.elcomercio.software.activity.ApiActivity;
import xyz.elcomercio.software.activity.PointOfSaleActivity;
import xyz.elcomercio.software.activity.PointOfSaleHasProductActivity;
import xyz.elcomercio.software.activity.R;
import xyz.elcomercio.software.entity.Category;
import xyz.elcomercio.software.expandable.listeners.ListItemListener;
import xyz.elcomercio.software.expandable.viewholders.ViewHolder;
import xyz.elcomercio.software.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Category> listData;
    private Category object;
    private Context context;
    private ListItemListener listItemListener;
    private boolean isExpanded = false;

    public CategoryAdapter(Context aContext, ArrayList<Category> listData) {
        this.context = aContext;
        this.listData = listData;
//        layoutInflater = LayoutInflater.from(aContext);
    }

    public void setListItemListener(ListItemListener listItemListener) {
        this.listItemListener = listItemListener;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        object = listData.get(position);

        TextView item = holder.item;
        item.setText(object.getId() + " -- " + object.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navigateToPointOfSaleHasProduct();

                Utils.longToast(context, "id 333: " + object.getId());

                if (isExpanded) {
                    listItemListener.onCollapse();
                    isExpanded = false;
                } else {
                    listItemListener.onExpand();
                    isExpanded = true;
                }
            }
        });
    }

    private void navigateToPointOfSaleHasProduct()
    {
        Intent intent = new Intent();
        intent.setClass(this.context, PointOfSaleHasProductActivity.class);
        this.context.startActivity(intent);
//        this.context.finish();
//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

}