package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Institution;
import br.com.vsgdev.socialsoftware.models.Item;
import br.com.vsgdev.socialsoftware.utils.InstituitionChoseAdapter;


public class NewItem2 extends Activity implements View.OnClickListener {

    private ListView lvInstitutions;
    private Button next;
    private InstituitionChoseAdapter instituitionChoseAdapter;
    private Item item;

    @Override
    public void onClick(View view) {
        if (next.isPressed()) {
            if (instituitionChoseAdapter.getSelected().isEmpty()) {
                Toast.makeText(this, getString(R.string.you_need_to_chose_at_least_one), Toast.LENGTH_SHORT).show();
            } else {
                item.setInstitutions(instituitionChoseAdapter.getSelected());
                Intent newItem3 = new Intent(this, NewItem3.class);
                newItem3.putExtra("ITEM", item);
                startActivity(newItem3);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item_2);
        item = (Item) getIntent().getSerializableExtra("ITEM");
        Institution i1 = new Institution(1, "Lar de maria", null, null);
        Institution i2 = new Institution(2, "APIPA", null, null);
        Institution i3 = new Institution(3, "Teste1", null, null);
        Institution i4 = new Institution(4, "teste2", null, null);
        Institution i5 = new Institution(5, "teste3", null, null);
        Institution i6 = new Institution(6, "test54", null, null);
        Institution i7 = new Institution(7, "La", null, null);
        Institution i8 = new Institution(8, "teste56", null, null);
        Institution i9 = new Institution(9, "teste7", null, null);
        ArrayList<Institution> institutions = new ArrayList<>();
        institutions.add(i1);
        institutions.add(i2);
        institutions.add(i3);
        institutions.add(i4);
        institutions.add(i5);
        institutions.add(i6);
        institutions.add(i7);
        institutions.add(i8);
        institutions.add(i9);

        lvInstitutions = (ListView) findViewById(R.id.lv_institutions_new_item_2);
        instituitionChoseAdapter = new InstituitionChoseAdapter(this, institutions);
        lvInstitutions.setAdapter(instituitionChoseAdapter);
        next = (Button) findViewById(R.id.btn_next_new_item_2);
        next.setOnClickListener(this);
    }
}
