package xyz.tianos.software.expandable.viewholders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import xyz.tianos.software.activity.R;

// Static inner class to initialize the views of rows
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView item;

    public ViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        item = (TextView) itemView.findViewById(R.id.row_item);
    }

    @Override
    public void onClick(View view) {
        Log.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
    }

}