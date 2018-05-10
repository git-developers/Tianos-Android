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
import xyz.tianos.software.utils.Const;
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
            convertView = layoutInflater.inflate(R.layout.adapter_point_of_sale, null);

            holder = new ViewHolder();
            holder.ivThumbnail = (ImageView) convertView.findViewById(R.id.iv_thumbnail);
            holder.tvText = (TextView) convertView.findViewById(R.id.tv_text);
            holder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PointOfSale object = this.listData.get(position);
        holder.tvText.setText(object.getName());
        holder.tvId.setText("id: " + object.getId());

        int imageId = Utils.getResourceIdByName(context, object.getImage(), Const.DEF_TYPE_DRAWABLE);
        holder.ivThumbnail.setImageResource(imageId);

        return convertView;
    }

    static class ViewHolder {
        ImageView ivThumbnail;
        TextView tvText;
        TextView tvId;
    }

}
