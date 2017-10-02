package com.wondermelonpapajoanne.joanne.parcel2go.DatabaseHandler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wondermelonpapajoanne.joanne.parcel2go.Utility.FirebaseTableConstant;

/**
 * Created by Sam on 7/23/2017.
 */

public class FirebaseHelper {

    public static DatabaseReference UserReference = FirebaseDatabase.getInstance().getReference().child(FirebaseTableConstant.Users);
    public static DatabaseReference AllDeliveryListReference = FirebaseDatabase.getInstance().getReference().child(FirebaseTableConstant.AllDeliveryList);
}
