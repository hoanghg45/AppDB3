package com.example.appdb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appdb3.Models.Patient;
import com.example.appdb3.Others.DBManager;

public class InsertActivity extends AppCompatActivity {
    public EditText edtName,edtCost;
    public Spinner spnPla;
    public RadioButton rbTn,rbTt;
    public RadioGroup rg;
    public Button btnIs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        edtName = findViewById(R.id.edtName);
        edtCost = findViewById(R.id.edtCost);
        spnPla = findViewById(R.id.spnPla);
        rbTn = findViewById(R.id.rbTn);
        rbTt = findViewById(R.id.rbTt);
        btnIs = findViewById(R.id.btnIs);
        rg = findViewById(R.id.rg);
        DBManager dbManager = new DBManager(this);
        String[] arraySpinner1 = new String[] {
                "KS Hoàn Vũ", "KS Diamond", "KS RiverSide"
        };
        String[] arraySpinner2 = new String[] {
                "Quận 1", "Quận BT", "Quận GV","Quận 9","Quận 7"
        };
        changeSpn(arraySpinner1);

       rbTt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               changeSpn(arraySpinner1);
           }
       });
        rbTn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeSpn(arraySpinner2);
            }
        });

        ///////////////

        btnIs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String form ="";
                if(rbTt.isChecked()){
                    form = "tt";
                }
                else form = "tn";
                Patient patient = new Patient(0,edtName.getText().toString(),form,spnPla.getSelectedItem().toString(),Integer.parseInt(edtCost.getText().toString()));
                dbManager.insertPatient(patient);
                Toast.makeText(getApplicationContext(), "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InsertActivity.this,MainActivity.class));
            }
        });
    }

    /////////////
    public  void changeSpn(String[] arraySpinner ){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPla.setAdapter(adapter);
    }
}