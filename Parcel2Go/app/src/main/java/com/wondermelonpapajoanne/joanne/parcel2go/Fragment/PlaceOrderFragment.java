package com.wondermelonpapajoanne.joanne.parcel2go.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.wondermelonpapajoanne.joanne.parcel2go.Activity.Customer.InputSenderInformationAndReceiverActivity;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.FB_DeliveryItem;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.Item;
import com.wondermelonpapajoanne.joanne.parcel2go.R;
import com.wondermelonpapajoanne.joanne.parcel2go.Utility.ItemTypeConstants;

public class PlaceOrderFragment extends Fragment {

    private EditText orderDescription;
    private Button documentItemBtn;
    private Spinner dcumentItemSpinner;
    private Button luggageItemBtn;
    private Spinner luggageItemSpinner;
    private Button parcelItemBtn;
    private Spinner parcelItemSpinner;
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

        //set up all the elements
        documentItemBtn = (Button) view.findViewById(R.id.button_document);
        luggageItemBtn = (Button) view.findViewById(R.id.button_luggage);
        parcelItemBtn = (Button) view.findViewById(R.id.button_parcel);
        orderDescription = (EditText) view.findViewById(R.id.edit_text_order_description);
        dcumentItemSpinner = (Spinner) view.findViewById(R.id.spinner_document_quantity);
        luggageItemSpinner = (Spinner) view.findViewById(R.id.spinner_luggage_quantity);
        parcelItemSpinner = (Spinner) view.findViewById(R.id.spinner_parcel_quantity);

        SetUpButtonListener();
        SetUpSpinnerListener();

        //Next button click function
        nextBtn = (Button) view.findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item Document = new Item();
                Document.Type = ItemTypeConstants.DOCUMENT_TYPE;
                Document.Quantity = documentQuantity;

                Item Parcel = new Item();
                Parcel.Type = ItemTypeConstants.PARCEL_TYPE;
                Parcel.Quantity = parcelQuantity;

                Item Luggage = new Item();
                Luggage.Type = ItemTypeConstants.LUGGAGE_TYPE;
                Luggage.Quantity = luggageQuantity;

                FB_DeliveryItem deliveryItem = new FB_DeliveryItem();
                deliveryItem._itemDescription = orderDescription.getText().toString();
                deliveryItem._documentItem = Document;
                deliveryItem._lugaggeItem = Luggage;
                deliveryItem._parcelItem = Parcel;

                InputSenderAndReceiverInformationPage(deliveryItem);
            }
        });

        return view;
    }

    private void SetUpButtonListener()
    {
        //test data dummy data
        documentItemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        luggageItemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        parcelItemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }

    private void SetUpSpinnerListener()
    {
        documentQuantity = 0;
        luggageQuantity = 0;
        parcelQuantity = 0;

        //set up quantity dropdown list
        ArrayAdapter<CharSequence> itemQuantityAdapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.quantity, android.R.layout.simple_spinner_item);
        itemQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dcumentItemSpinner.setAdapter(itemQuantityAdapter);
        dcumentItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                documentQuantity = ConvertIdTOQuantity((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                documentQuantity = 0;
            }
        });

        itemQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        luggageItemSpinner.setAdapter(itemQuantityAdapter);
        luggageItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                luggageQuantity = ConvertIdTOQuantity((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                luggageQuantity = 0;
            }
        });

        itemQuantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parcelItemSpinner.setAdapter(itemQuantityAdapter);
        parcelItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                parcelQuantity = ConvertIdTOQuantity((int) id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                parcelQuantity = 0;
            }
        });
    }

    private int ConvertIdTOQuantity(int Id)
    {
        int quantity = 0;
        switch(Id){
            case 1:
                quantity = 5;
                break;
            case 2:
                quantity = 10;
                break;
            case 3:
                quantity = 20;
                break;
            case 4:
                quantity = 21;
                break;
        }

        return quantity;
    }

    private void InputSenderAndReceiverInformationPage(FB_DeliveryItem deliveryItem){
        Intent intent = new Intent(PlaceOrderFragment.this.getActivity(), InputSenderInformationAndReceiverActivity.class);
        intent.putExtra(ItemTypeConstants.DeliveryItem, deliveryItem);

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
