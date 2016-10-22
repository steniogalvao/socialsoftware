package br.com.vsgdev.socialsoftware.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Institution;

public class InstituitionListAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Institution> objectsList;
    private ArrayList<Institution> selected = new ArrayList<>();

    private ArrayList<ItemHolder> itemHolders = new ArrayList<>();

    public InstituitionListAdapter(Context context, ArrayList<Institution> objectsList) {
        this.context = context;
        this.objectsList = objectsList;
    }

    @Override
    public int getCount() {
        return objectsList.size();
    }

    @Override
    public Institution getItem(int position) {
        return objectsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = null;
        ItemHolder itemHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row_fragment_institutions, parent, false);
            itemHolder = new ItemHolder();
            itemHolder.ivLogo = (ImageView) rowView.findViewById(R.id.iv_logo_row_fragment_institution);
            itemHolder.tvName = (TextView) rowView.findViewById(R.id.tv_name_row_fragment_institution);
            itemHolder.tvDescription = (TextView) rowView.findViewById(R.id.tv_description_row_fragment_institution);

            rowView.setTag(itemHolder);
            itemHolders.add(itemHolder);


        } else {
            rowView = convertView;
            itemHolder = (ItemHolder) rowView.getTag();
        }
        itemHolder.tvName.setText(objectsList.get(position).getName());
        itemHolder.tvDescription.setText((objectsList.get(position).getDescription()));
//        itemHolder.ivLogo.setImageResource();
        return rowView;
    }

    private class ItemHolder {
        public ImageView ivLogo;
        public TextView tvName;
        public TextView tvDescription;
    }
}



