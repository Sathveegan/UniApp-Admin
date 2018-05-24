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

public class SemesterRegAdapter extends ArrayAdapter{

    private Context mContext;
    private ArrayList<SemesterRegistration> semRegLists ;

    public SemesterRegAdapter(Context context, ArrayList<SemesterRegistration> semesterRegistrations) {
        super(context, R.layout.layout_semester_registration_view, semesterRegistrations);
        this.mContext = context;
        this.semRegLists = semesterRegistrations;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        final SemesterRegistration semesterRegistration = semRegLists.get(position);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.layout_semester_registration_view, null);
        }

        TextView itnumber = (TextView) view.findViewById(R.id.semester_reg_list_itnumber);
        TextView name = (TextView) view.findViewById(R.id.semester_reg_list_name);
        TextView date = (TextView) view.findViewById(R.id.semester_reg_list_date);
        TextView year = (TextView) view.findViewById(R.id.semester_reg_list_year);
        TextView semester = (TextView) view.findViewById(R.id.semester_reg_list_semester);
        TextView amount = (TextView) view.findViewById(R.id.semester_reg_list_amount);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        itnumber.setText(": "+semesterRegistration.getItnumber());
        name.setText(": "+semesterRegistration.getName());
        date.setText(": "+format.format(semesterRegistration.getDate()));
        year.setText(": "+semesterRegistration.getYear());
        semester.setText(": "+semesterRegistration.getSemester());
        amount.setText(": "+semesterRegistration.getAmount());

        return view;
    }
}
