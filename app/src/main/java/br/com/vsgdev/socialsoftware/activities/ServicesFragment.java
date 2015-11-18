package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.app.FragmentManager;
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

public class ServicesFragment extends Fragment implements ListView.OnItemClickListener, FloatingActionButton.OnClickListener {
    private ListView lvServices;
    private ArrayList<Item> serviceList = new ArrayList<>();
    private FloatingActionButton newItem, myItems;

    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        newItem = (FloatingActionButton) view.findViewById(R.id.fab_new_item_fragment_servives);
        newItem.setOnClickListener(this);
        myItems = (FloatingActionButton) view.findViewById(R.id.fab_my_items_fragment_servives);
        myItems.setOnClickListener(this);
        //buscar lista de serviços cadastrados
        User user = new User(0, "Stenio", "Galvao", "steniogalvao@gmail.com", null, null, null, null);
        Item service = new Item(0, "formatacao de computadores é um nome bem longo", "descricao sobre formatacao de computadores bem extensa para saber como vai se comportar se não ouver mais espaço", new BigDecimal(50), 1, true, user, null, null);
        serviceList.add(service);
        ListView lvService = (ListView) view.findViewById(R.id.lv_services_fragment_services);
        ServicesAdapter servicesAdapter = new ServicesAdapter(view.getContext(), serviceList);
        lvService.setAdapter(servicesAdapter);
        lvService.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = new ItemDetailsFragment();
        Bundle item = new Bundle();
        item.putSerializable("ITEM", serviceList.get(position));
        fragment.setArguments(item);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment, "ITEM_DETAIL").addToBackStack("ITEM_DETAIL")
                .commit();
    }

    @Override
    public void onClick(View v) {
        if (newItem.isPressed()) {
            Intent newItemIntent = new Intent(this.getActivity(), NewItem1.class);
            startActivity(newItemIntent);
        }
        if (myItems.isPressed()) {
            Fragment myItemsFragment = new MyItemsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.mainContent, myItemsFragment, "MY_ITEMS").addToBackStack("MY_ITEMS")
                    .commit();
        }

    }
}
