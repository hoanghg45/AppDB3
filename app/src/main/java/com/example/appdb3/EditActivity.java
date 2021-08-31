package com.example.appdb3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdb3.Models.Patient;
import com.example.appdb3.Others.DBManager;

public class EditActivity extends AppCompatActivity {
    public EditText edtName,edtCost,edtDays,edtId;
    public Spinner spnPla;
    public RadioButton rbTn,rbTt;
    public Button btnEdt,btnDel,btnCal;
    public TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtName = findViewById(R.id.edtName);
        edtCost = findViewById(R.id.edtCost);
        spnPla = findViewById(R.id.spnPla);
        rbTn = findViewById(R.id.rbTn);
        rbTt = findViewById(R.id.rbTt);
        btnEdt = findViewById(R.id.btnEdt);
        btnDel = findViewById(R.id.btnDel);
        btnCal = findViewById(R.id.btnCal);
        edtDays = findViewById(R.id.edtDays);
        txtResult = findViewById(R.id.txtResult);
        edtId = findViewById(R.id.edtId);

        String[] arraySpinner1 = new String[] {
                "KS Hoàn Vũ", "KS Diamond", "KS RiverSide"
        };
        String[] arraySpinner2 = new String[] {
                "Quận 1", "Quận BT", "Quận GV","Quận 9","Quận 7"
        };
        DBManager dbManager = new DBManager(this);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Patient patient = dbManager.getPatientByID(id);
        edtName.setText(patient.getName());
        edtId.setText(String.valueOf(id));
        edtCost.setText(String.valueOf(patient.getCost()));
        if(patient.getForm().toString().equals("tt")){
            rbTt.setChecked(true);
            changeSpn(arraySpinner1);
            switch (patient.getPlace()){
                case "KS Hoàn Vũ": spnPla.setSelection(0);
                    break;
                case "KS Diamond": spnPla.setSelection(1);
                    break;
                case "KS RiverSide": spnPla.setSelection(2);
                    break;

                default:
                    break;
            }
        }
        else {
            rbTn.setChecked(true);
            changeSpn(arraySpinner2);
            switch (patient.getPlace()){
                case "Quận 1": spnPla.setSelection(0);
                    break;
                case "Quận BT": spnPla.setSelection(1);
                    break;
                case "Quận GV": spnPla.setSelection(2);
                    break;
                case "Quận 9": spnPla.setSelection(3);
                    break;
                case "Quận 7": spnPla.setSelection(4);
                    break;
                default:
                    break;
            }

        }
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


        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int days = Integer.parseInt(edtDays.getText().toString());
                int cost = Integer.parseInt(edtCost.getText().toString());
                txtResult.setText(patient.getName()+" - "+days+" ngày cách ly"+"\n"
                        +"Tổng: "+days+"x"+cost+"="+(days*cost)+"VNĐ");
            }
        });
    btnDel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dbManager.deletePatientByID(id);
            Toast.makeText(getApplicationContext(), "Đã xoá thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditActivity.this,MainActivity.class));
        }
    });

    btnEdt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String form ="";
            if(rbTt.isChecked()){
                form = "tt";
            }
            else form = "tn";
            Patient patient = new Patient(id,edtName.getText().toString(),form,spnPla.getSelectedItem().toString(),Integer.parseInt(edtCost.getText().toString()));
            dbManager.updatePatient(patient);
            Toast.makeText(getApplicationContext(), "Sửa nhân viên thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditActivity.this,MainActivity.class));
        }
    });



    }
    public  void changeSpn(String[] arraySpinner ){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnPla.setAdapter(adapter);
    }
}