package com.example.mystudentdatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Student> {
    Context mpContext;

    public   MyAdapter (Context c , int resource, ArrayList<Student> objects){
        super(c,resource,objects);
        mpContext =c;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mpContext);
        View rowConvertView = inflater.inflate(R.layout.row_layout,parent,false);


      int id   = getItem(position).getsId();
        //String id   = String.valueOf(getItem(position).getsId());

        String name = getItem(position).getsName();
        String city = getItem(position).getsCity();

        Student student = new Student(id,name,city);

        TextView idR = rowConvertView.findViewById(R.id.textViewId);
        TextView nameR = rowConvertView.findViewById(R.id.textViewName);
        TextView numberR = rowConvertView.findViewById(R.id.textViewCity);

        idR.setText(String.valueOf(id));
        nameR.setText(name);
        numberR.setText(city);

        return rowConvertView;
    }
}
