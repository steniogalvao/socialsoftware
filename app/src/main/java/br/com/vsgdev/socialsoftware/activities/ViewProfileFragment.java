package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.utils.UserLogedSingleton;


public class ViewProfileFragment extends Fragment implements TextView.OnClickListener {

    private TextView personal, adress, name;
    private Fragment personalFragment = new PersonalFragment();
    private Fragment adressFragment = new AdressFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_profile, container, false);
        personal = (TextView) view.findViewById(R.id.tv_personal_view_profile);
        personal.setOnClickListener(this);
        adress = (TextView) view.findViewById(R.id.tv_adress_view_profile);
        adress.setOnClickListener(this);
        name = (TextView) view.findViewById(R.id.tv_name_view_profile);
        name.setText(UserLogedSingleton.getInstance().getName() + " " + UserLogedSingleton.getInstance().getSurrname());
        usePersonalView(view.getContext());

        return view;

    }

    @Override
    public void onClick(View view) {
        if (personal.isPressed()) {
            usePersonalView(view.getContext());
        }
        if (adress.isPressed()) {
            useAdressView(view.getContext());
        }
    }

    private void usePersonalView(Context context) {
        Toast.makeText(context, "personal", Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.rl_body_view_profile, personalFragment, "PERSONAL_FRAGMENT").addToBackStack("PERSONAL_FRAGMENT")
                .commit();
    }

    private void useAdressView(Context context) {

        Toast.makeText(context, "adress", Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.rl_body_view_profile, adressFragment, "ADRESS_FRAGMENT").addToBackStack("ADRESS_FRAGMENT")
                .commit();
    }
}
