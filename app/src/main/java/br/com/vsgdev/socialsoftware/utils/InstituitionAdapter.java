package br.com.vsgdev.socialsoftware.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.models.Institution;
import br.com.vsgdev.socialsoftware.R;

public class InstituitionAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Institution> objectsList;
    private BigDecimal charityValue = BigDecimal.ZERO;
    private int charityPercentage;
    private ArrayList<ItemHolder> itemHolders = new ArrayList<>();

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = null;
        ItemHolder itemHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.seekbar_item, parent, false);
            itemHolder = new ItemHolder();
            itemHolder.tvName = (TextView) rowView.findViewById(R.id.tv_institution_item);
            itemHolder.sbValue = (SeekBar) rowView.findViewById(R.id.sb_item);
            itemHolder.btnValue = (Button) rowView.findViewById(R.id.btn_value_item);
            rowView.setTag(itemHolder);
            itemHolders.add(itemHolder);


        } else {
            rowView = convertView;
            itemHolder = (ItemHolder) rowView.getTag();
        }
        int aux = 100 % objectsList.size();
        if (aux != 0 && position == 0)
            itemHolder.sbValue.setProgress((100 / objectsList.size()) + aux);
        else
            itemHolder.sbValue.setProgress(100 / objectsList.size());

        System.out.println(itemHolder.sbValue.getProgress());
//            itemHolder.btnValue.setText(itemHolder.sbValue.getProgress());
        itemHolder.tvName.setText(objectsList.get(position).getName());
        itemHolder.name = objectsList.get(position).getName();
        System.out.println(objectsList.get(position).getName());
        itemHolder.sbValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int max = 100;
                    int lastChangedIndex = -1;
                    boolean added;
                    int a = 2 % 3;
                    int dif = progress % itemHolders.size() - 1;

                    //DIVIDIR e não ter rest
                    if ((progress / itemHolders.size() - 1) != 0 && (progress % itemHolders.size() - 1) == 0) {
                        for (ItemHolder item : itemHolders) {
                            if (!seekBar.equals(item)) {
                                item.sbValue.setProgress(progress / itemHolders.size() - 1);
                            }
                        }

                    }
                    //nao dividir e ter resto
                    if ((progress / itemHolders.size() - 1) == 0 && (progress % itemHolders.size() - 1) != 0) {
                        int aux = progress % itemHolders.size() - 1;
                        for (ItemHolder item : itemHolders) {
                            if (!seekBar.equals(item) && lastChangedIndex != itemHolders.indexOf(item)) {
                                item.sbValue.setProgress(item.sbValue.getProgress() + aux);
                                lastChangedIndex = itemHolders.indexOf(item);
                                break;
                            }
                        }
                    }
                    //dividir e ter resto
                    if ((progress / itemHolders.size() - 1) != 0 && (progress % itemHolders.size() - 1) == 0) {
                        for (ItemHolder item : itemHolders) {
                            //verificar se não é o item e se não é o ultimo salvo
                            if (!seekBar.equals(item)) {
                                item.sbValue.setProgress(progress / itemHolders.size() - 1);
                            }
                        }

                    }
//                    if(progress/){
//
//                    }
                    //Todo: calcular se a mudança é divisivel por todo o array, se for dividir igualmente, se não dividir e somar a diferença ao primeiro indice, se ele não for o alterado
                }
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
        if (itemHolders.size() > objectsList.size())
            itemHolders.remove(itemHolders.size() - 1);
        return rowView;
    }

    public void setCharityValue(BigDecimal charityValue) {
        //porcentagem da caridade, porcentagem de cada instituição

        for (ItemHolder item : itemHolders) {
//            BigDecimal b = new BigDecimal(item.sbValue.getProgress()).setScale(2);
            BigDecimal auxCharityValue = charityValue.multiply(new BigDecimal(item.sbValue.getProgress()).divide(new BigDecimal(100)));
            item.btnValue.setText("R$ " + auxCharityValue.toString());
        }

    }


    private class ItemHolder {
        public String name;
        public TextView tvName;
        public SeekBar sbValue;
        public Button btnValue;

    }
}
