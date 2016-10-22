package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.utils.ValidateUtils;
import br.com.vsgdev.socialsoftware.utils.WebServiceUtils;


public class NewUser2 extends Activity implements View.OnClickListener {

    private Button next;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_2);
        email = (EditText) findViewById(R.id.et_email_new_user_2);
        next = (Button) findViewById(R.id.btn_next_new_user_2);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (next.isPressed()) {
            email = ValidateUtils.checkEmptyWithErro(email, getApplicationContext(), getString(R.string.this_field_is_required));
            if (email.getError() == null) {
                WebServiceUtils.validateEmail(email.getText().toString(),this, new EmailHandler(this));
            }
        }
    }

    //Handler para verificar se o email ja foi cadastrado
    public static class EmailHandler extends Handler {
        private Boolean valido;
        private Context context;

        public EmailHandler(Context context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            valido = bundle.getBoolean("valido");
            if (valido) {
                Intent newUser3 = new Intent(context, NewUser3.class);
                context.startActivity(newUser3);
            }
        }

    }

}

