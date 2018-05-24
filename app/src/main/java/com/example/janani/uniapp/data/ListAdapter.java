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
 * Created by Sathveegan on 30-Apr-18.
 */

public class ListAdapter extends ArrayAdapter {

    private Context mContext;
    private ArrayList<Post> postsList ;

    public ListAdapter(Context context, ArrayList<Post> posts) {
        super(context, R.layout.layout_list_view, posts);
        this.mContext = context;
        this.postsList = posts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final Post post = postsList.get(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_list_view, null);
        }

        TextView title = (TextView) view.findViewById(R.id.list_view_title);
        TextView date = (TextView) view.findViewById(R.id.list_view_date);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        title.setText(": "+post.getTitle());
        date.setText(": "+format.format(post.getDate()));

        return view;
    }

}
