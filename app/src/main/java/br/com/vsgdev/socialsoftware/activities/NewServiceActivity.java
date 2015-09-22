package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SeekBar;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Charity;
import br.com.vsgdev.socialsoftware.models.Institution;
import br.com.vsgdev.socialsoftware.utils.CharityAdapter;

/**
 * Created by root on 9/17/15.
 */
public class NewServiceActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar sbToMe;
    private Button btnValueToMe;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);
        sbToMe = (SeekBar) findViewById(R.id.sb_me_new_service);
        btnValueToMe = (Button) findViewById(R.id.btn_value_tome_new_service);
        sbToMe.setOnSeekBarChangeListener(this);
        Institution i1 = new Institution("Lar de maria");
        Institution i2 = new Institution("APIPA");
        Institution i3 = new Institution("Teste");
        ArrayList<Institution> institutions = new ArrayList<>();
        institutions.add(i1);
        institutions.add(i2);
        institutions.add(i3);
        Charity charity = new Charity(institutions);
        ArrayList<Charity> charities = new ArrayList<>();
        charities.add(charity);

        ExpandableListView lvCharities = (ExpandableListView) findViewById(R.id.lv_charity_new_service);
        ExpandableListAdapter expandableListAdapter = new CharityAdapter(this, charities);
        lvCharities.setAdapter(expandableListAdapter);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (sbToMe.isPressed()) {
            value = String.valueOf(progress);
            System.out.println(value);
            btnValueToMe.setText("R$ " + value);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
