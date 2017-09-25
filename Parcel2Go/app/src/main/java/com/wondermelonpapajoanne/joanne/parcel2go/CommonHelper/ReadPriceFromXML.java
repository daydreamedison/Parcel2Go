package com.wondermelonpapajoanne.joanne.parcel2go.CommonHelper;

import android.util.Xml;

import com.wondermelonpapajoanne.joanne.parcel2go.Model.ItemObject;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.VendorPriceRate;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 7/24/2017.
 */

public class ReadPriceFromXML {

    public List<VendorPriceRate> GetAllVendorsPriceRate(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            return readResources(parser);
        } finally {
            in.close();
        }
    }

    private List<VendorPriceRate> readResources(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<VendorPriceRate> vendors = new ArrayList<VendorPriceRate>();
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == XmlPullParser.END_DOCUMENT) {
                System.out.println("End document");
                break;
            } else if(eventType == XmlPullParser.START_TAG) {
                vendors.add(readVendor(parser));
            }
            eventType = parser.next();
        }
        return vendors;
    }

    private VendorPriceRate readVendor(XmlPullParser parser) throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        VendorPriceRate vendor = new VendorPriceRate();

        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            String name = parser.getName();
            if(parser.getEventType()  == XmlPullParser.START_TAG){
                if( name == null || name.isEmpty()) {
                    parser.next();
                    continue;
                }
                else {
                    if (name.equals("vendor")) {
                        vendor.name = parser.getAttributeValue(null, "name");
                        vendor.estimateDeliveryTime = parser.getAttributeValue(null, "estimatetime");
                        vendor.Parcel = readItems(parser, "Parcel");
                        vendor.Document = readItems(parser, "Document");
                        return vendor;
                    }
                }
            }
            else if(eventType == XmlPullParser.END_TAG)
            {
                String endName = parser.getName();
                if(endName.equals("vendor"))
                {
                    break;
                }
            }
            eventType = parser.next();
        }
        return vendor;
    }

    private ItemObject readItems(XmlPullParser parser, String input) throws XmlPullParserException, IOException
    {
        int eventType = parser.getEventType();
        ItemObject item = new ItemObject();
        while(parser.getEventType() != XmlPullParser.END_DOCUMENT)
        {
            String name = parser.getName();
            if(eventType == XmlPullParser.START_TAG){
                if( name == null || name.isEmpty()) {
                    parser.next();
                    continue;
                }
                else {
                    if (name.equals(input)) {
                        item.name = name;
                        item.priceRate = readPrice(parser);
                        return item;
                    }
                }
            }
            if(parser.getEventType() == XmlPullParser.END_TAG)
            {
                String endName = parser.getName();
                if(endName.equals(input))
                {
                    return item;
                }
            }
            eventType = parser.next();
        }

        return item;
    }

    private String readPrice(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        int eventType = parser.getEventType();
        //List<PriceRateModel> priceRateModelList = new ArrayList<PriceRateModel>();
        String priceRate = null;
        while(parser.getEventType()  != XmlPullParser.END_DOCUMENT)
        {
            String name = parser.getName();
            if(eventType == XmlPullParser.START_TAG){
                if( name == null || name.isEmpty()) {
                    parser.next();
                    continue;
                }
                else {
                    if (name.equals("pricerate")) {
                        priceRate =  parser.getAttributeValue(null, "rate");
                        return priceRate;
                    }
                }
            }
            else if(parser.getEventType() == XmlPullParser.END_TAG)
            {
                String endName = parser.getName();
                if(endName.equals("Parcel") || endName.equals("Document"))
                {
                    return null;
                }
            }
            eventType = parser.next();
        }
        return priceRate;
    }
}
