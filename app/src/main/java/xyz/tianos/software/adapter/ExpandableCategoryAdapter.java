package xyz.tianos.software.adapter;

import xyz.tianos.software.activity.ExpandableCategoryActivity;
import xyz.tianos.software.activity.R;
import xyz.tianos.software.entity.Category;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.expandable.listeners.ListItemListener;
import xyz.tianos.software.expandable.viewholders.ViewHolder;
import xyz.tianos.software.utils.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ExpandableCategoryAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Category> listData;
    private Category object;
    private Context context;
    private ListItemListener listItemListener;
    private boolean isExpanded = false;

    public ExpandableCategoryAdapter(Context aContext, ArrayList<Category> listData) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_place_item, parent, false);
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

                Utils.longToast(context, "id: " + object.getId());

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

}