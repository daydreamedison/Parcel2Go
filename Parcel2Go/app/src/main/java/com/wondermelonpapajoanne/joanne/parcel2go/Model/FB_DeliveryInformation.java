package com.wondermelonpapajoanne.joanne.parcel2go.Model;

import java.io.Serializable;

/**
 * Created by Sam on 9/16/2017.
 */

public class FB_DeliveryInformation{

    public FB_ReceiverInformation _senderInfo;
    public FB_ReceiverInformation _receiverInfo;
    public FB_DeliveryItem _deliveryItem;
    public String OTP;
    public String DeliveryStatus;
    public String DriverKey;
}
