package com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.wondermelonpapajoanne.joanne.parcel2go.Model.FB_DeliveryInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.FB_DeliveryItem;
import com.wondermelonpapajoanne.joanne.parcel2go.Model.FB_ReceiverInformation;
import com.wondermelonpapajoanne.joanne.parcel2go.R;

import java.util.List;

/**
 * Created by Sam on 10/29/2017.
 */

public class DeliveryListViewAdapter extends ArrayAdapter<FB_DeliveryInformation> {

    private final Context context;
    private List<FB_DeliveryInformation> deliveryList;
    private LayoutInflater inflater;

    public DeliveryListViewAdapter(Context context, List<FB_DeliveryInformation> deliveryList){
        super(context, R.layout.driver_delivery_list_item , deliveryList);

        this.context = context;
        this.deliveryList = deliveryList;
    }

    public int getCount() {
        return (deliveryList == null) ? 0 : deliveryList.size();
    }

    public View getView(int position, View convertView, ViewGroup viewGroup){
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(convertView == null){
            convertView = inflater.inflate(R.layout.driver_delivery_list_item, viewGroup, false);
        }


        if(deliveryList.size() > 0 ) {
            TextView DeliveryDescription = (TextView) convertView.findViewById(R.id.delivery_desscription);
            TextView DeliveryOfferedPrice = (TextView) convertView.findViewById(R.id.delivery_offered_price);
            TextView DeliveryLocation = (TextView) convertView.findViewById(R.id.delivery_location);

            FB_DeliveryInformation delivery = deliveryList.get(position);
            FB_DeliveryItem deliveryItem = delivery._deliveryItem;
            String description = deliveryItem._itemDescription;

            DeliveryDescription.setText(deliveryList.get(position)._deliveryItem._itemDescription);
            DeliveryOfferedPrice.setText( getTotalPrice(deliveryList.get(position)._deliveryItem));
            DeliveryLocation.setText( getLocation(deliveryList.get(position)._receiverInfo));
        }else
        {
            Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
        }


        return convertView;
    }

    private String getLocation(FB_ReceiverInformation receiver){
        return receiver.Address + " "
                + receiver.City + " "
                + receiver.State;
    }

    public String getTotalPrice(FB_DeliveryItem deliveryItem){
        double price = deliveryItem._lugaggeItem.Price +
                deliveryItem._parcelItem.Price +
                deliveryItem._documentItem.Price;

        return String.valueOf(price);
    }
}

