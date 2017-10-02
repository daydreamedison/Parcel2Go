package com.wondermelonpapajoanne.joanne.parcel2go.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.wondermelonpapajoanne.joanne.parcel2go.DatabaseHandler.FirebaseHelper;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.FB_DeliveryInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.GeneralDeliveryInfo;
import com.wondermelonpapajoanne.joanne.parcel2go.R;
import com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper.AllDeliveryListAdapter;

import java.util.ArrayList;
import java.util.List;

public class DriverAllDeliveryListFragment extends Fragment{

    private DatabaseReference db;

    public DriverAllDeliveryListFragment(){ }

    public static DriverAllDeliveryListFragment newInstance(String param1, String param2){
        DriverAllDeliveryListFragment fragment = new DriverAllDeliveryListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.driver_all_delivery_list_item, container, false);

        List<GeneralDeliveryInfo> deliveryList = GetAllDeliveryFromFirebase();

        final AllDeliveryListAdapter adapter = new AllDeliveryListAdapter(this.getActivity().getApplicationContext(),
                deliveryList);
        ListView listView = this.getActivity().findViewById(R.id.all_delivery_list);
        listView.setAdapter(adapter);

        return view;
    }

    private List<GeneralDeliveryInfo> GetAllDeliveryFromFirebase()
    {
        List<GeneralDeliveryInfo> deliveryList = new ArrayList<GeneralDeliveryInfo>();
        db = FirebaseHelper.AllDeliveryListReference;
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    //deliveryList.add(snapshot.getValue(FB_DeliveryInformation.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return deliveryList;
    }
}
