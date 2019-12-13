package com.example.mystudentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDb;

    EditText id;
    EditText name;
    EditText city;

    ListView listViewMain;
    Button addData;
    Button updateData;
    Button deleteData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.et_Id);
        name = findViewById(R.id.et_Name);
        city = findViewById(R.id.et_City);
        addData = findViewById(R.id.btn_Add);
        updateData = findViewById(R.id.btn_Update);
        listViewMain = findViewById(R.id.listView);
        deleteData  = findViewById(R.id.btn_Delete);

        myDb = new DatabaseHelper(this);


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Student mstudent    =
                myDb.addData(new Student(name.getText().toString(), city.getText().toString()));

                showData();


            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDb.updateData(id.getText().toString(),name.getText().toString(),city.getText().toString());
                showData();
            }
        });


        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String delId    =   id.getText().toString();

                myDb.deleteData(delId);
                showData();
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
