package com.example.janani.uniapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.janani.uniapp.R;
import com.example.janani.uniapp.core.FirebaseClient;
import com.example.janani.uniapp.data.Post;
import com.firebase.client.Firebase;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePostFragment extends Fragment {

    final static private String DB_URL = "https://androidapp-4830e-202705.firebaseio.com/";
    private FirebaseClient firebaseClient;

    public CreatePostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        final EditText title = view.findViewById(R.id.post_title);
        final DatePicker date = view.findViewById(R.id.post_date);
        final EditText message = view.findViewById(R.id.post_message);
        Button submit = view.findViewById(R.id.post_submit);

        firebaseClient = new FirebaseClient(DB_URL);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (title.getText().toString().trim().equals("")) {
                        title.setError("Title required!");
                    }

                    if (message.getText().toString().trim().equals("")) {
                        message.setError("Message Required!");
                    } else {
                        Post post = new Post(title.getText().toString().trim(), message.getText().toString().trim(), new Date(date.getYear()-1900, date.getMonth(), date.getDayOfMonth()));

                        firebaseClient.createPost(post);

                        Toast.makeText(getActivity(),"Post Added!",Toast.LENGTH_SHORT).show();

                        title.setText("");
                        message.setText("");
                    }
                } catch(Exception e){
                    Toast.makeText(getActivity(),"Try Again!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}



