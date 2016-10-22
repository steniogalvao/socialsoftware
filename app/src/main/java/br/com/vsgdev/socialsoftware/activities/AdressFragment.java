package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Address;
import br.com.vsgdev.socialsoftware.utils.UserLogedSingleton;
import br.com.vsgdev.socialsoftware.utils.WebServiceUtils;

public class AdressFragment extends Fragment {

    private EditText country, state, city, neighboard, street, number, complement;
    private Address address;

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

        if (UserLogedSingleton.getInstance().getAddress() != null) {
            country.setText(UserLogedSingleton.getInstance().getAddress().getCountry());
            state.setText(UserLogedSingleton.getInstance().getAddress().getState());
            city.setText(UserLogedSingleton.getInstance().getAddress().getCity());
            neighboard.setText(UserLogedSingleton.getInstance().getAddress().getNeighboard());
            street.setText(UserLogedSingleton.getInstance().getAddress().getStreet());
            number.setText(UserLogedSingleton.getInstance().getAddress().getNumber());
            complement.setText(UserLogedSingleton.getInstance().getAddress().getComplement());
        }
        return view;
    }

    @Override
    public void onStop() {
        if (UserLogedSingleton.getInstance().getAddress() != null) {
            address.setCity(city.getText().toString());
            address.setComplement(complement.getText().toString());
            address.setCountry(country.getText().toString());
            address.setNeighboard(neighboard.getText().toString());
            address.setNumber(number.getText().toString());
            address.setState(state.getText().toString());
            address.setStreet(street.getText().toString());
            WebServiceUtils.registreAddress(address, this.getActivity(), new AddressHandler(this.getActivity()));


        }
        super.onStop();
    }

    public static class AddressHandler extends Handler {
        private int id;
        private Context context;

        public AddressHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            id = bundle.getInt("id");
            Address address = (Address) bundle.getSerializable("address");
            if (id != 0) {
                UserLogedSingleton.getInstance().getAddress().setId(id);
                UserLogedSingleton.getInstance().getAddress().setCountry(address.getCountry());
                UserLogedSingleton.getInstance().getAddress().setState(address.getState());
                UserLogedSingleton.getInstance().getAddress().setCity(address.getCity());
                UserLogedSingleton.getInstance().getAddress().setNeighboard(address.getNeighboard());
                UserLogedSingleton.getInstance().getAddress().setStreet(address.getStreet());
                UserLogedSingleton.getInstance().getAddress().setNumber(address.getNumber());
                UserLogedSingleton.getInstance().getAddress().setComplement(address.getComplement());
                //TODO: update user
//                mSnackbar = Snackbar.make(getActivity().findViewById(R.id.coords_wrapper), "Loading", 1000000000);
            }
        }

    }
}
