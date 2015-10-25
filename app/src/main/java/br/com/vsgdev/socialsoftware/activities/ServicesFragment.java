package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
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

/**
 * Created by root on 10/5/15.
 */
public class ServicesFragment extends Fragment implements ListView.OnItemClickListener {
    private ListView lvServices;
    private ArrayList<Item> serviceList = new ArrayList<>();

    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        //buscar lista de serviços cadastrados
        User user = new User(0, "Stenio", "Galvao", null, null);
        Item service = new Item(0, "formatacao de computadores é um nome bem longo", "descricao sobre formatacao de computadores bem extensa para saber como vai se comportar se não ouver mais espaço", new BigDecimal(50), 1, true, user, null);
        serviceList.add(service);
        ListView lvService = (ListView) view.findViewById(R.id.lv_services_fragment_services);
        ServicesAdapter servicesAdapter = new ServicesAdapter(view.getContext(), serviceList);
        lvService.setAdapter(servicesAdapter);
        lvService.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("posicao:" + position);
        Fragment fragment = new ItemDetails();
        Bundle item = new Bundle();
        item.putSerializable("ITEM", serviceList.get(position));
        fragment.setArguments(item);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment, "ITEM_DETAIL").addToBackStack("ITEM_DETAIL")
                .commit();
    }
}
