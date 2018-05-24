package com.example.janani.uniapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.janani.uniapp.R;
import com.example.janani.uniapp.core.FirebaseClient;
import com.example.janani.uniapp.data.Post;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    final static private String DB_URL = "https://androidapp-4830e-202705.firebaseio.com/";
    private FirebaseClient firebaseClient;
    private ListView join_list_view;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        join_list_view = view.findViewById(R.id.list_view);

        firebaseClient = new FirebaseClient(getActivity(), DB_URL, join_list_view);
        firebaseClient.loadListPostData();

        SearchView searchPosts = (SearchView) view.findViewById(R.id.list_search);
        searchPosts.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                firebaseClient.searchListPosts(text);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                firebaseClient.searchListPosts(text);
                return true;
            }
        });

        join_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Post post = (Post) adapterView.getItemAtPosition(i);

                final Bundle bundle=new Bundle();
                final JoinListFragment joinListFragment = new JoinListFragment();

                bundle.clear();
                bundle.putSerializable("post",(Post) post);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                joinListFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragemant_main, joinListFragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
        });

        return view;
    }

}
