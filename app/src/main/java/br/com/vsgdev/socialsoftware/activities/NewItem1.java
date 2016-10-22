package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.models.Item;
import br.com.vsgdev.socialsoftware.utils.ValidateUtils;

public class NewItem1 extends Activity implements View.OnClickListener {

    private Button next;
    private EditText name, description, value, amount;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item_1);

        next = (Button) findViewById(R.id.btn_next_new_item_1);
        next.setOnClickListener(this);
        name = (EditText) findViewById(R.id.et_item_name_new_item_1);
        description = (EditText) findViewById(R.id.et_description_new_item_1);
        value = (EditText) findViewById(R.id.et_value_new_item_1);
        amount = (EditText) findViewById(R.id.et_amount_new_item_1);
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            Item item = (Item) bundle.getSerializable("ITEM");
            if (item != null) {
                name.setText(item.getName());
                description.setText(item.getDescription());
                value.setText(item.getValue().toString());
                amount.setText(String.valueOf(item.getQuantity()));
            }
        }


        //TODO: for test only, delete after implementation
//        if (createItem()) {
//            Intent newItem2 = new Intent(this, NewItem2.class);
//            newItem2.putExtra("ITEM", item);
//            startActivity(newItem2);
//        }
    }

    @Override
    public void onClick(View view) {
        if (next.isPressed()) {
            if (createItem()) {
                Intent newItem2 = new Intent(this, NewItem2.class);
                newItem2.putExtra("ITEM", item);
                startActivity(newItem2);
            }
        }
    }

    private Boolean createItem() {

        this.name = ValidateUtils.checkEmptyWithErro(this.name, getApplicationContext(), getString(R.string.this_field_is_required));
        this.description = ValidateUtils.checkEmptyWithErro(this.description, getApplicationContext(), getString(R.string.this_field_is_required));
        this.value = ValidateUtils.checkEmptyWithErro(this.value, getApplicationContext(), getString(R.string.this_field_is_required));
        this.amount = ValidateUtils.checkEmptyWithErro(this.amount, getApplicationContext(), getString(R.string.this_field_is_required));
        if (this.name.getError() == null && this.description.getError() == null && this.value.getError() == null && this.amount.getError() == null) {
            item = new Item(0, this.name.getText().toString(), this.description.getText().toString(), new BigDecimal(this.value.getText().toString()), Integer.valueOf(this.amount.getText().toString()), true, null, null, null);
            return true;
        } else
            return false;

    }

}
