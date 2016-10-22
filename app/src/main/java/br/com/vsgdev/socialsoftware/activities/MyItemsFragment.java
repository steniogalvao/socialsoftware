package br.com.vsgdev.socialsoftware.activities;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Item;
import br.com.vsgdev.socialsoftware.models.User;
import br.com.vsgdev.socialsoftware.utils.ServicesAdapter;
import br.com.vsgdev.socialsoftware.utils.UserLogedSingleton;
import br.com.vsgdev.socialsoftware.utils.WebServiceUtils;

public class MyItemsFragment extends Fragment implements ListView.OnItemClickListener {

    private ListView lvServices;
    private ArrayList<Item> serviceList = new ArrayList<>();
    private FloatingActionButton newItem, myItems;

    public MyItemsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_items, container, false);
        //buscar serviços cadastrados
//        WebServiceUtils.loadItemsFromUser(UserLogedSingleton.getInstance(), this.getActivity().getApplicationContext());
        Item service = new Item(0, "teste", "descricao sobre formatacao de computadores bem extensa para saber como vai se comportar se não ouver mais espaço", new BigDecimal(50), 1, true, UserLogedSingleton.getInstance(), null, null);
        serviceList.add(service);
        ListView lvService = (ListView) view.findViewById(R.id.lv_items_fragment_my_items);
        ServicesAdapter servicesAdapter = new ServicesAdapter(view.getContext(), serviceList);
        lvService.setAdapter(servicesAdapter);
        lvService.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent editItem = new Intent(this.getActivity(), NewItem1.class);
        editItem.putExtra("ITEM", serviceList.get(position));
        startActivity(editItem);
    }
}
