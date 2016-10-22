package br.com.vsgdev.socialsoftware.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Institution;

public class InstituitionChoseAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Institution> objectsList;
    private ArrayList<Institution> selected = new ArrayList<>();

    private ArrayList<ItemHolder> itemHolders = new ArrayList<>();

    public InstituitionChoseAdapter(Context context, ArrayList<Institution> objectsList) {
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
            rowView = inflater.inflate(R.layout.row_institution, parent, false);
            itemHolder = new ItemHolder();
            itemHolder.cbInstitution = (CheckBox) rowView.findViewById(R.id.cb_institution_row_institution);
            rowView.setTag(itemHolder);
            itemHolders.add(itemHolder);


        } else {
            rowView = convertView;
            itemHolder = (ItemHolder) rowView.getTag();
        }
        itemHolder.cbInstitution.setText(objectsList.get(position).getName());
        return rowView;
    }

    public ArrayList<Institution> getSelected() {
        selected.clear();
        for (ItemHolder itemHolder : itemHolders) {
            if (itemHolder.cbInstitution.isChecked())
                selected.add(objectsList.get(itemHolders.indexOf(itemHolder)));
        }
        return selected;
    }

    private class ItemHolder {
        public CheckBox cbInstitution;
    }
}



