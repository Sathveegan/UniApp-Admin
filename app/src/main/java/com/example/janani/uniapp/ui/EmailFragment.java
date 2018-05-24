package com.example.janani.uniapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.janani.uniapp.R;
import com.example.janani.uniapp.core.SendMailClient;
import com.example.janani.uniapp.data.JoinList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {


    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_email, container, false);

        JoinList joinList = (JoinList) getArguments().getSerializable("email");

        final EditText to = view.findViewById(R.id.email_to);
        final EditText subject = view.findViewById(R.id.email_subject);
        final EditText message = view.findViewById(R.id.email_message);

        to.setText(joinList.getEmail());

        Button submit = view.findViewById(R.id.email_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sto = to.getText().toString().trim();
                String ssubject = subject.getText().toString().trim();
                String smessage = message.getText().toString().trim();

                SendMailClient sm = new SendMailClient(getActivity(), sto, ssubject, smessage);

                sm.execute();

                subject.setText("");
                message.setText("");
            }




        });

        return view;
    }

}
