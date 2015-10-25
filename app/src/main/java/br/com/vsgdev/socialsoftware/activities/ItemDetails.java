package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Item;

/**
 * Created by root on 10/13/15.
 */
public class ItemDetails extends Fragment {
    private static TextView price, serviceName, serviceDescription, userName;


    public ItemDetails() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_item_details, container, false);
        price = (TextView) view.findViewById(R.id.tv_service_price_item_details);
        serviceName = (TextView) view.findViewById(R.id.tv_service_name_item_details);
        serviceDescription = (TextView) view.findViewById(R.id.tv_description_item_details);
        userName = (TextView) view.findViewById(R.id.tv_user_item_details);
        Bundle itemBundle = getArguments();
        Item item = (Item) itemBundle.getSerializable("ITEM");
        setView(item);
        return view;
    }

    public void setView(Item item) {
        ItemDetails itemDetails = new ItemDetails();
        price.setText("R$ " + item.getValue().toString());
        serviceName.setText(item.getName());
        serviceDescription.setText(item.getDescription());
        userName.setText(item.getUser().getName());

    }

}
