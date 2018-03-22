package xyz.tianos.software.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import xyz.tianos.software.activity.R;
import xyz.tianos.software.entity.PointOfSale;
import xyz.tianos.software.utils.Utils;

import java.util.List;

/**
 * Created by jafeth on 3/31/17.
 */

public class PointOfSaleAdapter extends BaseAdapter {

    private List<PointOfSale> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public PointOfSaleAdapter(Context aContext, List<PointOfSale> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.point_of_sale_adapter_item, null);
            holder = new ViewHolder();
            holder.userImageView = (ImageView) convertView.findViewById(R.id.imageView_user);
            holder.studentNameView = (TextView) convertView.findViewById(R.id.textView_studentName);
            holder.itemView = (TextView) convertView.findViewById(R.id.textView_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PointOfSale pos = this.listData.get(position);
        holder.studentNameView.setText(pos.getName());
        holder.itemView.setText("pos: " + pos.getId());

        int imageId = Utils.getResourceIdByName(context, pos.getImage(), "drawable");
        holder.userImageView.setImageResource(imageId);

        return convertView;
    }

    static class ViewHolder {
        ImageView userImageView;
        TextView studentNameView;
        TextView itemView;
    }

}
