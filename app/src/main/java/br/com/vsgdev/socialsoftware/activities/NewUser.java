package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.utils.UserLogedSingleton;
import br.com.vsgdev.socialsoftware.utils.ValidateUtils;


public class NewUser extends Activity implements View.OnClickListener {

    private Button next;
    private EditText name, surname;
    private TextView termOfUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_1);
        name = (EditText) findViewById(R.id.et_name_new_user);
        surname = (EditText) findViewById(R.id.et_surname_new_user);
        next = (Button) findViewById(R.id.btn_next_new_user_1);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (next.isPressed()) {
            name = ValidateUtils.checkEmptyWithErro(name, getApplicationContext(), getString(R.string.this_field_is_required));
            surname = ValidateUtils.checkEmptyWithErro(surname, getApplicationContext(), getString(R.string.this_field_is_required));
            if (name.getError() == null && surname.getError() == null) {
                UserLogedSingleton.getInstance().setName(name.getText().toString());
                UserLogedSingleton.getInstance().setSurrname(surname.getText().toString());
                Intent next = new Intent(this, NewUser2.class);
                startActivity(next);
            }
        }
    }
}
