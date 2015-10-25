package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.vsgdev.socialsoftware.R;

/**
 * Created by root on 10/1/15.
 */
public class NewUser2 extends Activity implements View.OnClickListener {

    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_2);
        next = (Button) findViewById(R.id.btn_next_new_user_2);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (next.isPressed()) {
            Intent next = new Intent(this, NewUser3.class);
            startActivity(next);
        }
    }
}
