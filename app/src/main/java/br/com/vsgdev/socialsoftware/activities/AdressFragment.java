package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.utils.UserLogedSingleton;

public class AdressFragment extends Fragment {

    private EditText country, state, city, neighboard, street, number, complement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adress_information, container, false);
        country = (EditText) view.findViewById(R.id.et_country_fragment_adress_information);
        state = (EditText) view.findViewById(R.id.et_state_fragment_adress_information);
        city = (EditText) view.findViewById(R.id.et_city_fragment_adress_information);
        neighboard = (EditText) view.findViewById(R.id.et_neighboard_fragment_adress_information);
        street = (EditText) view.findViewById(R.id.et_street_fragment_adress_information);
        number = (EditText) view.findViewById(R.id.et_number_fragment_adress_information);
        complement = (EditText) view.findViewById(R.id.et_phone_complement_personal_information);

        if (UserLogedSingleton.getInstance().getAdress() != null) {
            country.setText(UserLogedSingleton.getInstance().getAdress().getCountry());
            state.setText(UserLogedSingleton.getInstance().getAdress().getState());
            city.setText(UserLogedSingleton.getInstance().getAdress().getCity());
            neighboard.setText(UserLogedSingleton.getInstance().getAdress().getNeighboard());
            street.setText(UserLogedSingleton.getInstance().getAdress().getStreet());
            number.setText(UserLogedSingleton.getInstance().getAdress().getNumber());
            complement.setText(UserLogedSingleton.getInstance().getAdress().getComplement());
        }
        return view;
    }

    @Override
    public void onStop() {
        if (UserLogedSingleton.getInstance().getAdress() != null) {
            UserLogedSingleton.getInstance().getAdress().setCountry(country.getText().toString());
            UserLogedSingleton.getInstance().getAdress().setState(state.getText().toString());
            UserLogedSingleton.getInstance().getAdress().setCity(city.getText().toString());
            UserLogedSingleton.getInstance().getAdress().setNeighboard(neighboard.getText().toString());
            UserLogedSingleton.getInstance().getAdress().setStreet(street.getText().toString());
            UserLogedSingleton.getInstance().getAdress().setNumber(number.getText().toString());
            UserLogedSingleton.getInstance().getAdress().setComplement(complement.getText().toString());
        }
        super.onStop();
    }
}
