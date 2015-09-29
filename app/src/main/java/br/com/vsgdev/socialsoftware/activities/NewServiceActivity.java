package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Charity;
import br.com.vsgdev.socialsoftware.models.Institution;
import br.com.vsgdev.socialsoftware.utils.InstituitionAdapter;


public class NewServiceActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
    private SeekBar sbToMe, sbCharity;
    private EditText etServiceValue;
    private Button btnValueToMe, btnValueCharity;
    private BigDecimal charityValue;
    private int toMePercentage, charityPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);
        sbToMe = (SeekBar) findViewById(R.id.sb_me_new_service);
        btnValueToMe = (Button) findViewById(R.id.btn_value_tome_new_service);
        sbCharity = (SeekBar) findViewById(R.id.sb_charity_new_service);
        btnValueCharity = (Button) findViewById(R.id.btn_value_charity_new_service);
        etServiceValue = (EditText) findViewById(R.id.et_service_value_new_service);
        toMePercentage = 0;
        charityPercentage = 100;
        sbToMe.setProgress(toMePercentage);
        sbCharity.setProgress(charityPercentage);
        btnValueToMe.setText("R$ 0");
        btnValueCharity.setText("R$ 0");
        sbToMe.setOnSeekBarChangeListener(this);
        sbCharity.setOnSeekBarChangeListener(this);
        Institution i1 = new Institution(1, "Lar de maria", null, null);
        Institution i2 = new Institution(2, "APIPA", null, null);
        Institution i3 = new Institution(3, "Teste", null, null);
        ArrayList<Institution> institutions = new ArrayList<>();
        institutions.add(i1);
        institutions.add(i2);
        institutions.add(i3);
        Charity charity = new Charity(institutions);
        ArrayList<Charity> charities = new ArrayList<>();
        charities.add(charity);

        ListView lvCharities = (ListView) findViewById(R.id.lv_charity_new_service);
        final InstituitionAdapter instituitionAdapter = new InstituitionAdapter(this, institutions);
        lvCharities.setAdapter(instituitionAdapter);
        etServiceValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                BigDecimal value = new BigDecimal(s.toString());
                BigDecimal charityValue = value.multiply(new BigDecimal(sbCharity.getProgress() / 100));
                instituitionAdapter.setCharityValue(charityValue);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (sbToMe.isPressed()) {
            if (fromUser)
                sbCharity.setProgress(100 - progress);
        }
        if (sbCharity.isPressed()) {
            if (fromUser)
                sbToMe.setProgress(100 - progress);
        }
        checkSeekBarBlock();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void checkSeekBarBlock() {
        if (sbCharity.getProgress() < 30) {
            Toast.makeText(this, this.getString(R.string.minimum_donation), Toast.LENGTH_SHORT).show();
            sbCharity.setProgress(30);
            sbToMe.setProgress(70);
        }
    }

    private void refreshValues() {

    }

}
