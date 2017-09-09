package com.wondermelonpapajoanne.joanne.parcel2go.Activity.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wondermelonpapajoanne.joanne.parcel2go.Model.PlaceOrderObject;
import com.wondermelonpapajoanne.joanne.parcel2go.R;

import java.io.Serializable;

public class PlaceOrderAddressActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText street;
    private EditText city;
    private EditText receiverName;
    private EditText receiverPhonenumber;
    private EditText receiverStreet;
    private EditText receiverCity;

    //init object
    PlaceOrderObject placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        placeOrder = (PlaceOrderObject) intent.getSerializableExtra("placeorder");

        street = (EditText) findViewById(R.id.edit_text_street);
        city = (EditText) findViewById(R.id.edit_text_city);
        receiverName = (EditText) findViewById(R.id.edit_text_receiver_name);
        receiverPhonenumber = (EditText) findViewById(R.id.edit_text_receiver_phonenumber);
        receiverStreet = (EditText) findViewById(R.id.edit_text_street);
        receiverCity = (EditText) findViewById(R.id.edit_text_receiver_city);

        buttonNext = (Button) findViewById(R.id.btn_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = street.getText().toString() + " " + city.getText().toString();
                String dropOffLocation = receiverStreet.getText().toString()
                        + " " + receiverCity.getText().toString();

                placeOrder.pickUpLocation = location;
                placeOrder.dropOffUsername = receiverName.getText().toString();
                placeOrder.dropOffPhonenumber = receiverPhonenumber.getText().toString();
                placeOrder.dropOffLocation = dropOffLocation;

                GotoPriceComparisonActivity(placeOrder);
            }
        });
    }

    private void GotoPriceComparisonActivity(PlaceOrderObject placeOrder)
    {
        Intent intent = new Intent(PlaceOrderAddressActivity.this, PriceComparisonActivity.class);
        intent.putExtra("placeorder", (Serializable) placeOrder);
        PlaceOrderAddressActivity.this.startActivity(intent);
    }

}
