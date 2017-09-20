package com.wondermelonpapajoanne.joanne.parcel2go.Activity.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wondermelonpapajoanne.joanne.parcel2go.Model.DeliveryInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.DeliveryItem;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.Item;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.ItemList;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.ReceiverInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.R;
import com.wondermelonpapajoanne.joanne.parcel2go.Utility.ItemTypeConstants;

import java.io.Serializable;

public class InputSenderInformationAndReceiverActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText street;
    private EditText city;
    private EditText receiverName;
    private EditText receiverPhonenumber;
    private EditText receiverStreet;
    private EditText receiverCity;

    //init object
    DeliveryItem deliveryItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order_address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        deliveryItem = (DeliveryItem) intent.getSerializableExtra(ItemTypeConstants.DeliveryItem);

        //Sender Information
        street = (EditText) findViewById(R.id.edit_text_street);
        city = (EditText) findViewById(R.id.edit_text_city);

        //Receiver Information
        receiverName = (EditText) findViewById(R.id.edit_text_receiver_name);
        receiverPhonenumber = (EditText) findViewById(R.id.edit_text_receiver_phonenumber);
        receiverStreet = (EditText) findViewById(R.id.edit_text_receiver_street);
        receiverCity = (EditText) findViewById(R.id.edit_text_receiver_city);

        buttonNext = (Button) findViewById(R.id.btn_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 /*               String location = street.getText().toString() + " " + city.getText().toString();
                String dropOffLocation = receiverStreet.getText().toString()
                        + " " + receiverCity.getText().toString();*/

                /*placeOrder.pickUpLocation = location;
                placeOrder.dropOffUsername = receiverName.getText().toString();
                placeOrder.dropOffPhonenumber = receiverPhonenumber.getText().toString();
                placeOrder.dropOffLocation = dropOffLocation;*/

                ReceiverInformation sender = new ReceiverInformation();
                sender.Name = "joanne";
                sender.ContactNumber = "111";
                sender.Address = street.getText().toString() + " " + city.getText().toString();

                ReceiverInformation receiver = new ReceiverInformation();
                receiver.Name = receiverName.getText().toString();
                receiver.Address = receiverStreet.getText().toString() + "" + receiverCity.getText().toString();
                receiver.ContactNumber = receiverPhonenumber.getText().toString();


//                DeliveryInformation deliveryInformation = new DeliveryInformation(sender, receiver);
//                deliveryInformation.SaveDeliveryItem(deliveryItem);
                GotoPriceComparisonActivity(sender, receiver, deliveryItem);
            }
        });
    }

    private void GotoPriceComparisonActivity(ReceiverInformation sender, ReceiverInformation receiver, DeliveryItem deliveryItem)
    {
        Intent intent = new Intent(InputSenderInformationAndReceiverActivity.this, PriceComparisonActivity.class);
        intent.putExtra(ItemTypeConstants.SenderInformation, sender);
        intent.putExtra(ItemTypeConstants.ReceiverInformation, receiver);
        intent.putExtra(ItemTypeConstants.DeliveryItem, deliveryItem);
        InputSenderInformationAndReceiverActivity.this.startActivity(intent);
    }

}
