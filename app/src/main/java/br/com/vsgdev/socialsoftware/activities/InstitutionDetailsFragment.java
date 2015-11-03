package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Institution;

public class InstitutionDetailsFragment extends Fragment {
    private ImageView logo;
    private TextView name;
    private TextView description;

    public InstitutionDetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_institutions, container, false);
        name = (TextView) view.findViewById(R.id.tv_service_name_item_details);
        description = (TextView) view.findViewById(R.id.tv_description_item_details);
        Bundle itemBundle = getArguments();
        Institution institution = (Institution) itemBundle.getSerializable("INSTITUTION");
        setView(institution);
        return view;
    }

    public void setView(Institution institution) {
//        logo.setImageResource();
        name.setText(institution.getName());
        description.setText(institution.getDescription());

    }
}
