package com.example.mystudentdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDb;

    TextInputLayout id;
    TextInputLayout name;
    TextInputLayout city;

    ListView listViewMain;
    Button addData;
    Button updateData;
    Button deleteData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.textInputLayout_ID);
        name = findViewById(R.id.textInput_Name);
        city = findViewById(R.id.textInputLayout_City);
        addData = findViewById(R.id.button_Add);
        updateData = findViewById(R.id.button_Update);
        listViewMain = findViewById(R.id.recyclerView);
        deleteData  = findViewById(R.id.button_Delete);

        myDb = new DatabaseHelper(this);


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Student mstudent    =
                myDb.addData(new Student(name.getEditText().getText().toString(), city.getEditText().getText().toString()));

                showData();
                Toast.makeText(getApplicationContext(),name.getEditText().getText().toString()+ " ADDED",Toast.LENGTH_LONG).show();


            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.updateData(id.getEditText().getText().toString(),name.getEditText().getText().toString(),city.getEditText().getText().toString());
                showData();
                Toast.makeText(getApplicationContext(),"ID no." +id.getEditText().getText().toString()+ " UPDATED",Toast.LENGTH_LONG).show();
            }
        });


        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delId    =   id.getEditText().getText().toString();

                myDb.deleteData(delId);
                showData();
                Toast.makeText(getApplicationContext(),"ID no." +id.getEditText().getText().toString()+ " DELETED",Toast.LENGTH_LONG).show();
            }
        });




    }

    private void showData() {
        ArrayList<Student> studentArrayList = myDb.getAllStudents2();

        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), R.layout.row_layout, studentArrayList);

        listViewMain.setAdapter(myAdapter);

        for (Student sn : studentArrayList) {
            Log.d("key11", "Id: " + sn.getsId() + " ,Name: " + sn.getsName() + " ,City: " +
                    sn.getsCity());

        }
    }


}
