package com.wondermelonpapajoanne.joanne.parcel2go.RowAdapterHelper;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wondermelonpapajoanne.joanne.parcel2go.Model.VendorObject;
import com.wondermelonpapajoanne.joanne.parcel2go.R;

import java.util.List;

/**
 * Created by Sam on 7/23/2017.
 */

public class VendorPriceComparisonAdapter extends ArrayAdapter<VendorObject>{

    private final Context context;
    private List<VendorObject> vendorList;

    public VendorPriceComparisonAdapter(Context context, List<VendorObject> vendorList){
        super(context, R.layout.activity_price_comparison_item, vendorList);

        this.context = context;
        this.vendorList = vendorList;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_price_comparison_item, parent, false);

        TextView vendorNameTextView = (TextView) rowView.findViewById(R.id.vendor_name);
        TextView documentPriceRateTextView = (TextView) rowView.findViewById(R.id.document_price_rate);
        TextView parcelPriceRateTextView = (TextView) rowView.findViewById(R.id.parcel_price_rate);
        TextView luggagePriceRateTextView = (TextView) rowView.findViewById(R.id.luggage_price_rate);
        TextView deliveryWorkingDayTextView = (TextView) rowView.findViewById(R.id.delivery_working_day);

        vendorNameTextView.setText(vendorList.get(position).name);
        documentPriceRateTextView.setText(vendorList.get(position).Document.priceRate);
        documentPriceRateTextView.setText(vendorList.get(position).Parcel.priceRate);
        documentPriceRateTextView.setText(vendorList.get(position).Parcel.priceRate);
        deliveryWorkingDayTextView.setText(vendorList.get(position).estimateDeliveryTime);

        return rowView;
    }
}
