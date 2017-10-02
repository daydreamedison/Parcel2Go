package com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wondermelonpapajoanne.joanne.parcel2go.Model.GeneralDeliveryInfo;
import com.wondermelonpapajoanne.joanne.parcel2go.R;

import java.util.List;

/**
 * Created by Sam on 10/3/2017.
 */

public class AllDeliveryListAdapter extends ArrayAdapter<GeneralDeliveryInfo>{

    private final Context context;
    private List<GeneralDeliveryInfo> generalDeliveryInfos;

    public AllDeliveryListAdapter(Context context, List<GeneralDeliveryInfo> generalDeliveryInfos){
        super(context, R.layout.driver_all_delivery_list_item, generalDeliveryInfos);

        this.context = context;
        this.generalDeliveryInfos = generalDeliveryInfos;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.driver_all_delivery_list_item, parent, false);

        TextView DeliveryDescription = (TextView) rowView.findViewById(R.id.delivery_desscription);
        TextView DeliveryOfferedPrice = (TextView) rowView.findViewById(R.id.delivery_offered_price);
        TextView DeliveryLocation = (TextView) rowView.findViewById(R.id.delivery_location);

        DeliveryDescription.setText(generalDeliveryInfos.get(position).Description);
        DeliveryOfferedPrice.setText(generalDeliveryInfos.get(position).Price);
        DeliveryLocation.setText(generalDeliveryInfos.get(position).Location);

        return rowView;
    }
}
