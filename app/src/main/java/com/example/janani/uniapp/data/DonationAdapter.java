package com.example.janani.uniapp.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.janani.uniapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Sathveegan on 25-May-18.
 */

public class DonationAdapter extends ArrayAdapter{

    private Context mContext;
    private ArrayList<Donation> donationsList ;

    public DonationAdapter(Context context, ArrayList<Donation> donations) {
        super(context, R.layout.layout_donation_view, donations);
        this.mContext = context;
        this.donationsList = donations;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final Donation donation = donationsList.get(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_donation_view, null);
        }

        TextView name = (TextView) view.findViewById(R.id.donation_list_name);
        TextView date = (TextView) view.findViewById(R.id.donation_list_date);
        TextView amount = (TextView) view.findViewById(R.id.donation_list_amount);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        name.setText(": "+donation.getName());
        date.setText(": "+format.format(donation.getDate()));
        amount.setText(": "+donation.getAmount());

        return view;
    }
}
