package com.example.janani.uniapp.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janani.uniapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Sathveegan on 30-Apr-18.
 */

public class JoinListAdapter extends ArrayAdapter {

    private Context mContext;
    private ArrayList<JoinList> joinLists ;

    public JoinListAdapter(Context context, ArrayList<JoinList> joinLists) {
        super(context, R.layout.layout_join_list, joinLists);
        this.mContext = context;
        this.joinLists = joinLists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final JoinList joinList = joinLists.get(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_join_list, null);
        }

        final TextView username = (TextView) view.findViewById(R.id.join_list_username);
        final TextView email = (TextView) view.findViewById(R.id.join_list_email);

        username.setText(": "+joinList.getUsername());
        email.setText(": "+joinList.getEmail());


        return view;
    }
}
