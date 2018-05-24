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

public class RepeatRegAdapter extends ArrayAdapter {

    private Context mContext;
    private ArrayList<RepeatRegistration> repeatRegLists ;

    public RepeatRegAdapter(Context context, ArrayList<RepeatRegistration> repeatRegLists) {
        super(context, R.layout.layout_repeat_registration_view, repeatRegLists);
        this.mContext = context;
        this.repeatRegLists = repeatRegLists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final RepeatRegistration repeatRegistration = repeatRegLists.get(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_repeat_registration_view, null);
        }

        TextView itnumber = (TextView) view.findViewById(R.id.repeat_reg_list_itnumber);
        TextView name = (TextView) view.findViewById(R.id.repeat_reg_list_name);
        TextView date = (TextView) view.findViewById(R.id.repeat_reg_list_date);
        TextView subject = (TextView) view.findViewById(R.id.repeat_reg_list_subject);
        TextView amount = (TextView) view.findViewById(R.id.repeat_reg_list_amount);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        itnumber.setText(": "+repeatRegistration.getItnumber());
        name.setText(": "+repeatRegistration.getName());
        date.setText(": "+format.format(repeatRegistration.getDate()));
        subject.setText(": "+repeatRegistration.getSubject());
        amount.setText(": "+repeatRegistration.getAmount());

        return view;
    }

}
