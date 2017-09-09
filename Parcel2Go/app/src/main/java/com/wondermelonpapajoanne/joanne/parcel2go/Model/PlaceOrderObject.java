package com.wondermelonpapajoanne.joanne.parcel2go.Model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Sam on 7/15/2017.
 */

public class PlaceOrderObject implements Serializable{
    public Map<String, Integer> orderItems;
    public String itemDescription;
    public String pickUpLocation;
    public String pickUpUsername;
    public String pickUpPhonenumber;
    public String dropOffUsername;
    public String dropOffPhonenumber;
    public String dropOffLocation;

    public Map<String, Integer> GetAllOrderItems()
    {
        return orderItems;
    }

    public Integer GetQuantityByCategory(String category){
        switch(category)
        {
            case "document":
                return orderItems.get("document");
            case "parcel":
                return orderItems.get("parcel");
            case "luggage":
                return orderItems.get("luggage");
            default:
                return null;
        }
    }
}
