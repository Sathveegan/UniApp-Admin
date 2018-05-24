package com.example.janani.uniapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.janani.uniapp.R;
import com.example.janani.uniapp.core.FirebaseClient;
import com.example.janani.uniapp.data.JoinList;
import com.example.janani.uniapp.data.Post;

import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class JoinListFragment extends Fragment {

    final static private String DB_URL = "https://androidapp-4830e-202705.firebaseio.com/";
    private FirebaseClient firebaseClient;
    private ListView join_list_view;

    public JoinListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_join_list, container, false);

        Post post = (Post) getArguments().getSerializable("post");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        TextView title = view.findViewById(R.id.join_title);
        TextView date = view.findViewById(R.id.join_date);
        title.setText(": "+post.getTitle());
        date.setText(": "+format.format(post.getDate()));

        join_list_view = view.findViewById(R.id.join_list_view);

        firebaseClient = new FirebaseClient(getActivity(), DB_URL, join_list_view);
        firebaseClient.loadJoinPostData(post);

        join_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JoinList joinList = (JoinList) adapterView.getItemAtPosition(i);

                final Bundle bundle=new Bundle();
                final EmailFragment emailFragment = new EmailFragment();

                bundle.clear();
                bundle.putSerializable("email",(JoinList) joinList);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                emailFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragemant_main, emailFragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        return view;
    }

}
