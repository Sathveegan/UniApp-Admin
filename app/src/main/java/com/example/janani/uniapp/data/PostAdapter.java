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
 * Created by Sathveegan on 29-Apr-18.
 */

public class PostAdapter extends ArrayAdapter {

    private Context mContext;
    private ArrayList<Post> postsList ;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, R.layout.layout_post_view, posts);
        this.mContext = context;
        this.postsList = posts;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final Post post = postsList.get(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_post_view, null);
        }

        TextView title = (TextView) view.findViewById(R.id.post_view_title);
        TextView date = (TextView) view.findViewById(R.id.post_view_date);
        TextView message = (TextView) view.findViewById(R.id.post_view_message);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        title.setText(": "+post.getTitle());
        date.setText(": "+format.format(post.getDate()));
        message.setText(": "+post.getMessage());

        return view;
    }
}
