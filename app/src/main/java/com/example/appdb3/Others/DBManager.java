package com.example.appdb3.Others;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appdb3.Models.Patient;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CountryManger";
    private static final int VERSION = 1;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLQuery = "CREATE TABLE Patient (ID INTEGER primary key AUTOINCREMENT,NAME VARCHAR(255),FORM VARCHAR(255),PLACE VARCHAR(255),COST INTEGER)";
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public ArrayList<Patient> getAllPatient(){
        ArrayList<Patient> PatientList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Patient", null);

        //Đến dòng đầu của tập dữ liệu
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int PatientId = cursor.getInt(0);
            String PatientName = cursor.getString(1);
            String PatientForm = cursor.getString(2);
            String PatientPlace = cursor.getString(3);
            int PatientCost = cursor.getInt(4);


            PatientList.add(new Patient(PatientId, PatientName, PatientForm, PatientPlace, PatientCost));
            cursor.moveToNext();
        }

        cursor.close();

        return PatientList;
    }

public Patient getPatientByID(int ID) {
    Patient patient = null;
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("select * from Patient where id = ?", new String[]{ID + ""});

    if (cursor.getCount() > 0) {
        cursor.moveToFirst();
        int PatientId = cursor.getInt(0);
        String PatientName = cursor.getString(1);
        String PatientForm = cursor.getString(2);
        String PatientPlace = cursor.getString(3);
        int PatientCost = cursor.getInt(4);

        patient = new Patient(PatientId, PatientName, PatientForm, PatientPlace, PatientCost);
    }
    cursor.close();
    return patient;
}

    //cập nhật
    public void updatePatient(Patient patient) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Patient SET name=?, form = ?, place = ?,cost =? where id = ?",
                new String[]{patient.getName(),patient.getForm(),patient.getPlace(), patient.getCost()+"",patient.getID()+""});
    }

    //thêm mới
    public void insertPatient(Patient patient) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Patient ( name, form, place,cost ) VALUES (?,?,?,?)",
                new String[]{patient.getName(),patient.getForm(),patient.getPlace(), patient.getCost()+""});
    }

    //Xoá SV bằng ID
    public void deletePatientByID(int PatientID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM Patient where id = ?", new String[]{String.valueOf(PatientID)});
    }

}
