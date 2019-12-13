package com.example.mystudentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentsManager";
    private static final String TABLE_NAME = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CITY = "city";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_CITY + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_STUDENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);

    }


    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setsId(Integer.parseInt(cursor.getString(0)));
                student.setsName(cursor.getString(1));
                student.setsCity(cursor.getString(2));
                // Adding contact to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }


    public ArrayList<Student> getAllStudents2() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setsId(Integer.parseInt(cursor.getString(0)));
                student.setsName(cursor.getString(1));
                student.setsCity(cursor.getString(2));
                // Adding contact to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }

    void addData(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getsName());
        values.put(KEY_CITY, student.getsCity());
        db.insert(TABLE_NAME, null, values);
        Log.i("key11","add data");
        db.close();
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }


    public void updateData(String ID, String name, String city) {
       // SQLiteDatabase db = this.getWritableDatabase();

      /*  ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getsName());
        values.put(KEY_CITY, student.getsCity());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getsId()) });*/


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, ID);
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_CITY, city);
         db.update(TABLE_NAME, contentValues, KEY_ID + "=" + ID, null) ;
    }


    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();


        return cursor.getCount();
    }
}
