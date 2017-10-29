package com.wondermelonpapajoanne.joanne.parcel2go.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wondermelonpapajoanne.joanne.parcel2go.DatabaseHandler.FirebaseHelper;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.FB_DeliveryInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.GeneralDeliveryInfo;
import com.wondermelonpapajoanne.joanne.parcel2go.R;
import com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper.AllDeliveryListAdapter;
import com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper.DeliveryListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DriverAllDeliveryListFragment extends Fragment{

    private ListView listView;
    private ArrayList<FB_DeliveryInformation> firebaseDeliveryList = new ArrayList<>();
    private ArrayList<FB_DeliveryInformation> deliveryList = new ArrayList<>();
    public DeliveryListViewAdapter adapter;
    private DatabaseReference mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container
            , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.driver_all_delivery_list_item, container, false);

        GetAllDeliveryFromFirebase();

        /*final AllDeliveryListAdapter adapter = new AllDeliveryListAdapter(this.getActivity().getApplicationContext(),
                firebaseDeliveryList);
        ListView listView = this.getActivity().findViewById(R.id.all_delivery_list);
        listView.setAdapter(adapter);*/

        return view;
    }

    private void GetAllDeliveryFromFirebase()
    {
        DatabaseReference deliveryRef = FirebaseDatabase.getInstance().getReference().child("AllDeliveryList");
        deliveryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                deliveryList.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    FB_DeliveryInformation delivery = data.getValue(FB_DeliveryInformation.class);
                    deliveryList.add(delivery);
                }
                firebaseDeliveryList = deliveryList;

               /* adapter = new DeliveryListViewAdapter(getActivity().getApplicationContext(), firebaseDeliveryList);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
}
