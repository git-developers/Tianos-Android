package xyz.elcomercio.software.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import xyz.elcomercio.software.activity.R;
import xyz.elcomercio.software.entity.Product;
import xyz.elcomercio.software.utils.Const;
import xyz.elcomercio.software.utils.Utils;

public class PointOfSaleHasProductAdapter extends BaseAdapter {

    private List<Product> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms

    public PointOfSaleHasProductAdapter(Context aContext, List<Product> listData) {
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

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_point_of_sale_has_product, null);
            
            holder = new ViewHolder();
            holder.ivThumbnail = (ImageView) convertView.findViewById(R.id.iv_thumbnail);
            holder.tvText = (TextView) convertView.findViewById(R.id.tv_text);
            holder.tvId = (TextView) convertView.findViewById(R.id.tv_id);
            holder.productQuantity = (EditText) convertView.findViewById(R.id.et_cantidad_producto);

            addTextChangedListener(holder, position);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();

            removeTextChangedListener(holder);
            addTextChangedListener(holder, position);
        }

        /**
         * OBJECTO DENTRO DE CADA LIST VIEW
         */
        Product object = this.listData.get(position);
        holder.tvText.setText(object.getName());
        holder.tvId.setText("id: " + object.getId());

        String quantity = object.getCantidadProduct() == 0 ? "" : String.valueOf(object.getCantidadProduct());
        holder.productQuantity.setText(quantity);

        int imageId = Utils.getResourceIdByName(context, object.getImage(), Const.DEF_TYPE_DRAWABLE);
        holder.ivThumbnail.setImageResource(imageId);

        return convertView;
    }

    static class ViewHolder {
        ImageView ivThumbnail;
        TextView tvText;
        TextView tvId;
        EditText productQuantity;
        TextChangedListener textChangedListener;

        public EditText getProductQuantity() {
            return productQuantity;
        }

        public TextChangedListener getTextChangedListener() {
            return textChangedListener;
        }

        public void setTextChangedListener(TextChangedListener textChangedListener) {
            this.textChangedListener = textChangedListener;
        }
    }

    class TextChangedListener implements TextWatcher
    {
        private int position;

        TextChangedListener(int position)
        {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            Integer quantity = 0;

            try {

                Log.d("TEXTOXX", "POSICION::: " + position + " -- CANTIDAD:: " + s.toString() );

                quantity = Integer.parseInt(s.toString().trim());

            } catch (NumberFormatException e) {

            }

            listData.get(position).setCantidadProduct(quantity);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    }

    private void removeTextChangedListener(ViewHolder viewHolder)
    {
        TextChangedListener textChangedListener = viewHolder.getTextChangedListener();
        EditText productQuantity = viewHolder.getProductQuantity();
        productQuantity.removeTextChangedListener(textChangedListener);
    }

    private void addTextChangedListener(ViewHolder viewHolder, int position)
    {
        TextChangedListener textChangedListener = new TextChangedListener(position);
        EditText productQuantity = viewHolder.getProductQuantity();
        productQuantity.addTextChangedListener(textChangedListener);
        viewHolder.setTextChangedListener(textChangedListener);
    }

}
