package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

import br.com.vsgdev.socialsoftware.R;

/**
 * Created by root on 9/30/15.
 */
public class NewUser extends Activity implements View.OnClickListener {

    private Button next;
    private TextView termOfUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_1);
        next = (Button) findViewById(R.id.btn_next_new_user_1);
        next.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (next.isPressed()) {
            Intent next = new Intent(this, NewUser2.class);
            startActivity(next);
        }
    }
}
