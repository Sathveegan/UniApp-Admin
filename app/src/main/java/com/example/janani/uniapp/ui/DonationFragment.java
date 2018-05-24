package com.example.janani.uniapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.janani.uniapp.R;
import com.example.janani.uniapp.core.FirebaseClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFragment extends Fragment {

    final static private String DB_URL = "https://androidapp-4830e-202705.firebaseio.com/";
    private FirebaseClient firebaseClient;
    private SwipeMenuListView donation_list;

    public DonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation, container, false);

        donation_list = view.findViewById(R.id.donation_list);

        firebaseClient = new FirebaseClient(getActivity(), DB_URL, donation_list);
        firebaseClient.loadDonationData();

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
                deleteItem.setBackground(R.color.redColor);
                deleteItem.setWidth(250);
                deleteItem.setIcon(R.drawable.ic_delete_white_24dp);
                menu.addMenuItem(deleteItem);
            }
        };

        donation_list.setMenuCreator(creator);

        donation_list.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        firebaseClient.deleteDonation(position);
                        Toast.makeText(getActivity(), "Donation Deleted!", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        return view;
    }

}
