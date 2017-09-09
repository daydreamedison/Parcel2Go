package com.wondermelonpapajoanne.joanne.parcel2go.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wondermelonpapajoanne.joanne.parcel2go.Activity.Customer.PlaceOrderAddressActivity;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.PlaceOrderObject;
import com.wondermelonpapajoanne.joanne.parcel2go.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PlaceOrderFragment extends Fragment {

    private EditText orderDescription;
    private Button documentItem;
    private Spinner documentItemQuantity;
    private Button luggageItem;
    private Spinner luggageItemQuantity;
    private Button parcelItem;
    private Spinner parcelItemQuantity;
    private Button addPhoto;
    private Button nextBtn;

    //init value
    private boolean isDocumentBtnChecked ;
    private int documentQuantity ;
    private boolean isLuggageBtnChecked ;
    private int luggageQuantity ;
    private boolean isParcelBtnChecked ;
    private int parcelQuantity;

    private OnFragmentInteractionListener mListener;

    public PlaceOrderFragment() {
        // Required empty public constructor
    }

    public static PlaceOrderFragment newInstance(String param1, String param2) {
        PlaceOrderFragment fragment = new PlaceOrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place_order, container, false);

        documentItem = (Button) view.findViewById(R.id.button_document);
        luggageItem = (Button) view.findViewById(R.id.button_luggage);
        parcelItem = (Button) view.findViewById(R.id.button_parcel);
        orderDescription = (EditText) view.findViewById(R.id.edit_text_order_description);
        documentItemQuantity = (Spinner) view.findViewById(R.id.spinner_document_quantity);
        luggageItemQuantity = (Spinner) view.findViewById(R.id.spinner_luggage_quantity);
        luggageItemQuantity = (Spinner) view.findViewById(R.id.spinner_luggage_quantity);
        parcelItemQuantity = (Spinner) view.findViewById(R.id.spinner_parcel_quantity);

        isDocumentBtnChecked = false;
        documentQuantity = 0;
        documentItem = (Button) view.findViewById(R.id.button_document);
        documentItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isDocumentBtnChecked) {
                    isDocumentBtnChecked = false;
                    documentQuantity = 10;
                }
                else {
                    isDocumentBtnChecked = true;
                    documentQuantity = 0;
                }
            }
        });

        isLuggageBtnChecked = false;
        luggageQuantity = 0;
        luggageItem = (Button) view.findViewById(R.id.button_luggage);
        luggageItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isLuggageBtnChecked) {
                    isLuggageBtnChecked = false;
                    luggageQuantity = 9;
                }
                else {
                    isLuggageBtnChecked = true;
                    luggageQuantity = 0;
                }
            }
        });

        isParcelBtnChecked = false;
        parcelQuantity = 0;
        parcelItem = (Button) view.findViewById(R.id.button_parcel);
        parcelItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isParcelBtnChecked) {
                    isParcelBtnChecked = false;
                    parcelQuantity = 8;
                }
                else {
                    isParcelBtnChecked = true;
                    parcelQuantity = 0;
                }
            }
        });

        //set spinner drop down
        ArrayAdapter<CharSequence> itemQuantityAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.quantity, android.R.layout.simple_spinner_item);
        itemQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        documentItemQuantity.setAdapter(itemQuantityAdapter);

        itemQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        luggageItemQuantity.setAdapter(itemQuantityAdapter);

        itemQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parcelItemQuantity.setAdapter(itemQuantityAdapter);

        nextBtn = (Button) view.findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Integer> orderItem = new HashMap<String, Integer>();
                orderItem.put("Document", documentQuantity);
                orderItem.put("Parcel", parcelQuantity);
                orderItem.put("luggage", luggageQuantity);

                PlaceOrderObject placeOrder = new PlaceOrderObject();
                placeOrder.orderItems = orderItem;
                placeOrder.itemDescription = orderDescription.getText().toString();
                //placeOrder.itemDescription = "hahaha";

                GotoPlaceOrderAddressFragment(placeOrder);
            }
        });

        return view;
    }



    private void GotoPlaceOrderAddressFragment(PlaceOrderObject placeOrder){
        Intent intent = new Intent(PlaceOrderFragment.this.getActivity(), PlaceOrderAddressActivity.class);
        intent.putExtra("placeorder", (Serializable) placeOrder);
        PlaceOrderFragment.this.startActivity(intent);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onPlaceOrderFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onPlaceOrderFragmentInteraction(Uri uri);
    }
}
