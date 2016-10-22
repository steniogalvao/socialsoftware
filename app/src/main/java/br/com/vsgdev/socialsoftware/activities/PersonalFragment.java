package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.utils.UserLogedSingleton;
import br.com.vsgdev.socialsoftware.utils.ValidateUtils;


public class PersonalFragment extends Fragment {

    private EditText name, surname, email, phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_information, container, false);
        name = (EditText) view.findViewById(R.id.et_name_fragment_personal_information);
        surname = (EditText) view.findViewById(R.id.et_surname_fragment_personal_information);
        email = (EditText) view.findViewById(R.id.et_email_fragment_personal_information);
        phone = (EditText) view.findViewById(R.id.et_phone_fragment_personal_information);

        //setar informações do user loged
        name.setText(UserLogedSingleton.getInstance().getName());
        surname.setText(UserLogedSingleton.getInstance().getSurrname());
        email.setText(UserLogedSingleton.getInstance().getEmail());
        phone.setText(UserLogedSingleton.getInstance().getPhone());

        return view;

    }

    @Override
    public void onStop() {
        name = ValidateUtils.checkEmptyWithErro(name, getActivity().getApplicationContext(), getString(R.string.this_field_is_required));
        surname = ValidateUtils.checkEmptyWithErro(surname, getActivity().getApplicationContext(), getString(R.string.this_field_is_required));
        email = ValidateUtils.checkEmptyWithErro(email, getActivity().getApplicationContext(), getString(R.string.this_field_is_required));
        if (name.getError() == null)
            UserLogedSingleton.getInstance().setName(name.getText().toString());
        if (surname.getError() == null)
            UserLogedSingleton.getInstance().setSurrname(surname.getText().toString());
        if (email.getError() == null)
            UserLogedSingleton.getInstance().setEmail(email.getText().toString());
        if (!ValidateUtils.checkEmpty(phone))
            UserLogedSingleton.getInstance().setPhone(phone.getText().toString());
        super.onDetach();
    }
}
