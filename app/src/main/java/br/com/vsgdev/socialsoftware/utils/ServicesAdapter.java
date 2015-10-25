package br.com.vsgdev.socialsoftware.utils;

import android.app.Service;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Institution;
import br.com.vsgdev.socialsoftware.models.Item;

/**
 * Created by root on 10/5/15.
 */
public class ServicesAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Item> objectsList;


    public ServicesAdapter(Context context, ArrayList<Item> objectsList) {
        this.context = context;
        this.objectsList = objectsList;
    }

    @Override
    public int getCount() {
        return objectsList.size();
    }

    @Override
    public Object getItem(int position) {
        return objectsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        ServiceHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.service_list_item, parent, false);
            holder = new ServiceHolder();
            holder.holderDescription = (TextView) rowView.findViewById(R.id.tv_description_service_item);
            holder.holderPicture = (ImageView) rowView.findViewById(R.id.iv_service_picture_service_item);
            holder.holderPrice = (TextView) rowView.findViewById(R.id.tv_service_price_service_item);
            holder.holderService = (TextView) rowView.findViewById(R.id.tv_service_name_service_item);
            holder.holderUser = (TextView) rowView.findViewById(R.id.tv_user_service_item);
            rowView.setTag(holder);

        } else {
            rowView = convertView;
            holder = (ServiceHolder) rowView.getTag();
        }
        holder.holderDescription.setText(objectsList.get(position).getDescription());
        holder.holderUser.setText(objectsList.get(position).getUser().getName());
        holder.holderService.setText(objectsList.get(position).getName());
        holder.holderPrice.setText(objectsList.get(position).getValue().toString());

        return rowView;
    }

    class ServiceHolder {
        private ImageView holderPicture;
        private TextView holderService;
        private TextView holderDescription;
        private TextView holderPrice;
        private TextView holderUser;

    }
}
