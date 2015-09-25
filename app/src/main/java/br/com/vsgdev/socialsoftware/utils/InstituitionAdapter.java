package br.com.vsgdev.socialsoftware.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.models.Institution;
import br.com.vsgdev.socialsoftware.R;

public class InstituitionAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Institution> objectsList;

    public InstituitionAdapter(Context context, ArrayList<Institution> objectsList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        final ItemHolder itemHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.seekbar_item, parent, false);
            itemHolder = new ItemHolder();
            itemHolder.tvName = (TextView) rowView.findViewById(R.id.tv_institution_item);
            itemHolder.sbValue = (SeekBar) rowView.findViewById(R.id.sb_item);
            itemHolder.btnValue = (Button) rowView.findViewById(R.id.btn_value_item);

            rowView.setTag(itemHolder);

        } else {
            rowView = convertView;
            itemHolder = (ItemHolder) rowView.getTag();
        }
        itemHolder.tvName.setText(objectsList.get(position).getName());
        itemHolder.sbValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String value = String.valueOf(progress);
//                System.out.println(value);
                itemHolder.btnValue.setText("R$ " + value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        itemHolder.sbValue.setOnSeekBarChangeListener();
        itemHolder.btnValue.setText("R$ " + itemHolder.sbValue.getProgress());
//        System.out.println("posicao: " + position + "name: " + objectsList.get(position).getName());

        return rowView;
    }

    private class ItemHolder {

        public TextView tvName;
        public SeekBar sbValue;
        public Button btnValue;
    }
}
