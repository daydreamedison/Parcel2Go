package com.wondermelonpapajoanne.joanne.parcel2go.Activity.Customer;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wondermelonpapajoanne.joanne.parcel2go.CommonHelper.ReadPriceFromXML;
import com.wondermelonpapajoanne.joanne.parcel2go.DatabaseHandler.FirebaseHelper;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.ItemObject;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.PlaceOrderObject;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.PriceRateModel;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.VendorObject;
import com.wondermelonpapajoanne.joanne.parcel2go.R;
import com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper.VendorPriceComparisonAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static java.lang.System.in;

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
        final PlaceOrderObject placeOrder = (PlaceOrderObject) intent.getSerializableExtra("placeorder");
        List<VendorObject> allVendorObject = new ArrayList<VendorObject>();

        btnContinue = (Button) findViewById(R.id.button_continue_placeorder);
        btnContinue.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    writeNewData(placeOrder);
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

    private void writeNewData(PlaceOrderObject placeOrderObject)
    {
        DatabaseReference example = mDatabase.child("Orders");

        HashMap<String, Object> result = new HashMap<>();
        result.put("name", "sam");
        result.put("password", "asdf1234");

        String key = mDatabase.child("Orders").push().getKey();
        example.child(key).setValue(result);
    }

}
