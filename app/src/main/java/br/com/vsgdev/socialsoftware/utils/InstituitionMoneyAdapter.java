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

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Institution;

public class InstituitionMoneyAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<Institution> objectsList;
    private BigDecimal charityValue = BigDecimal.ZERO;
    private int charityPercentage;
    private ArrayList<ItemHolder> itemHolders = new ArrayList<>();
    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    int lastChangedIndex = 0;

    public InstituitionMoneyAdapter(Context context, ArrayList<Institution> objectsList) {
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
                if (fromUser && itemHolders.size() > 1) {
                    int diference = 0;
                    int currentItemPosition = -1;
                    int rest = 0;
                    int div = 0;

                    for (ItemHolder itemHolder : itemHolders) {
                        if (itemHolder.sbValue.equals(seekBar)) {
                            diference = 100 - progress;
                            currentItemPosition = itemHolders.indexOf(itemHolder);
                            rest = diference % (itemHolders.size() - 1);
                            div = diference / (itemHolders.size() - 1);
                        }
                    }

                    //DIVIDIR e não ter resto = somar resultado ao progress
                    if ((div != 0) && (rest == 0)) {
                        for (ItemHolder itemHolder : itemHolders) {
                            if (!itemHolder.sbValue.equals(seekBar)) {
                                itemHolder.sbValue.setProgress(div);
                            }
                        }
                        refreshValues();
                    }
                    //dividir e ter resto= somar resultado ao progress e jogar resto em algum
                    if ((div != 0) && (rest != 0)) {
                        for (ItemHolder itemHolder : itemHolders) {
                            if (!itemHolder.sbValue.equals(seekBar)) {
                                itemHolder.sbValue.setProgress(div);
                            }
                        }
                        lastChangedIndex = pickOneToChange(currentItemPosition, lastChangedIndex, itemHolders);
                        itemHolders.get(lastChangedIndex).sbValue.setProgress(itemHolders.get(lastChangedIndex).sbValue.getProgress() + rest);
                        refreshValues();
                    }
                    //nao dividir e ter resto = jogar resto em algum
                    if ((div == 0) && (rest != 0)) {
                        lastChangedIndex = pickOneToChange(currentItemPosition, lastChangedIndex, itemHolders);
                        itemHolders.get(lastChangedIndex).sbValue.setProgress(itemHolders.get(lastChangedIndex).sbValue.getProgress() + rest);
                        refreshValues();
                    }
                    //nao dividir e nao ter resto
                    System.out.println(">>>PRogress" + progress);

                    //Todo: calcular se a mudança é divisivel por todo o array, se for dividir igualmente, se não dividir e somar a diferença ao primeiro indice, se ele não for o alterado
                } else {
                    itemHolders.get(0).sbValue.setProgress(100);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        itemHolder.btnValue.setText("R$ " + calcPercentage(charityValue, new BigDecimal(itemHolder.sbValue.getProgress())));
        if (itemHolders.size() > objectsList.size())
            itemHolders.remove(itemHolders.size() - 1);
        return rowView;
    }

    public void setCharityValue(BigDecimal charityValue) {
        this.charityValue = charityValue;
        refreshValues();
    }


    private class ItemHolder {
        public String name;
        public TextView tvName;
        public SeekBar sbValue;
        public Button btnValue;

    }

    private BigDecimal calcPercentage(BigDecimal value, BigDecimal percentage) {
        //TODO: do something, its round is losing 1 cent
        return value.multiply(percentage).divide(ONE_HUNDRED).setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }

    private int pickOneToChange(int itemPosition, int lastChange, ArrayList<ItemHolder> arrayList) {
        int toChange = lastChange;
        for (ItemHolder o : arrayList) {
            //se nao for o objeto e nem for o ultimo alterado, pode mudar
            if (!arrayList.get(itemPosition).equals(o) && !arrayList.get(toChange).equals(o)) {
                toChange = arrayList.indexOf(o);
            } else {
                toChange = toChange++;
                if (toChange > arrayList.size())
                    toChange = 0;
            }
        }
        return toChange;
    }

    private void refreshValues() {
        for (ItemHolder itemHolder : itemHolders) {
            itemHolder.btnValue.setText("R$ " + calcPercentage(charityValue, new BigDecimal(itemHolder.sbValue.getProgress())));
        }
    }
}



