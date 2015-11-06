package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.vsgdev.socialsoftware.R;


public class ViewProfileFragment extends Fragment implements TextView.OnClickListener {

    private TextView personal, adress;
    private Fragment personalFragment = new PersonalFragment();
    private Fragment adressFragment = new AdressFragment();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_profile, container, false);
        personal = (TextView) view.findViewById(R.id.tv_personal_view_profile);
        adress = (TextView) view.findViewById(R.id.tv_adress_view_profile);

        return view;

    }

    @Override
    public void onClick(View view) {
        if (personal.isPressed()) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.rl_body_view_profile, personalFragment, "PERSONAL_FRAGMENT").addToBackStack("PERSONAL_FRAGMENT")
                    .commit();
        }
        if (adress.isPressed()) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.rl_body_view_profile, adressFragment, "ADRESS_FRAGMENT").addToBackStack("ADRESS_FRAGMENT")
                    .commit();
        }
    }
}
