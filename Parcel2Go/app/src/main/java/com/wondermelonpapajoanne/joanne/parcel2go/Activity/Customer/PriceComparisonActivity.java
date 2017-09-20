package com.wondermelonpapajoanne.joanne.parcel2go.Activity.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wondermelonpapajoanne.joanne.parcel2go.CommonHelper.ReadPriceFromXML;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.DeliveryInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.DeliveryItem;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.ItemList;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.ReceiverInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.VendorObject;
import com.wondermelonpapajoanne.joanne.parcel2go.R;
import com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper.VendorPriceComparisonAdapter;
import com.wondermelonpapajoanne.joanne.parcel2go.Utility.FirebaseTableConstant;
import com.wondermelonpapajoanne.joanne.parcel2go.Utility.ItemTypeConstants;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PriceComparisonActivity extends AppCompatActivity {

    private static final String ns = null;
    private Button btnContinue;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_comparison);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        ReceiverInformation receiverInformation = (ReceiverInformation) intent.getSerializableExtra(ItemTypeConstants.ReceiverInformation);
        ReceiverInformation senderInformation = (ReceiverInformation) intent.getSerializableExtra(ItemTypeConstants.SenderInformation);
        DeliveryItem deliveryItem = (DeliveryItem) intent.getSerializableExtra(ItemTypeConstants.DeliveryItem);

        final DeliveryInformation deliveryInformation = new DeliveryInformation();
        deliveryInformation._deliveryItem = deliveryItem;
        deliveryInformation._receiverInfo = receiverInformation;
        deliveryInformation._senderInfo = senderInformation;

        List<VendorObject> allVendorObject = new ArrayList<VendorObject>();

        btnContinue = (Button) findViewById(R.id.button_continue_placeorder);
        btnContinue.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    writeNewData(deliveryInformation);
                }

        });


        try {
            InputStream in = getResources().openRawResource(R.raw.vendor_price_rate);

            if(in != null) {
                ReadPriceFromXML xml = new ReadPriceFromXML();
                allVendorObject = xml.GetAllVendorsPriceRate(in);
            }

            final VendorPriceComparisonAdapter adapter = new VendorPriceComparisonAdapter(this.getApplicationContext(),
                    allVendorObject);
            ListView listView = (ListView) this.findViewById(R.id.confirmation_item_list);
            listView.setAdapter(adapter);
        }
        catch(Exception ex){ }
    }

    private void writeNewData(DeliveryInformation deliveryInformation)
    {
        DatabaseReference example = mDatabase.child(FirebaseTableConstant.AllDeliveryList);

/*
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", "sam");
        result.put("password", "asdf1234");
*/

        String key = mDatabase.child(FirebaseTableConstant.AllDeliveryList).push().getKey();
        example.child(key).setValue(deliveryInformation);
    }


}
