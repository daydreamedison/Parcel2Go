package com.wondermelonpapajoanne.joanne.parcel2go.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sam on 9/16/2017.
 */

public class DeliveryItem implements Serializable{

    public Item _documentItem;
    public Item _parcelItem;
    public Item _lugaggeItem;
    public String _itemDescription;
}
