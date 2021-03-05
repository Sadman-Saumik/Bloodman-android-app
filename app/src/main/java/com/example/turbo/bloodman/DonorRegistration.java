package com.example.turbo.bloodman;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DonorRegistration extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String regBtype,regLocation;
    Spinner blsp,lsp;
    String bType[] = {"A+","B+","AB+","O+","A-","B-","AB-","O-"};
    ArrayAdapter <String> adapterb;
    String locat[] = {"Dhaka","Rajshahi","Barishal","Khulna","Chattagram","Sylhet","Rangpur","Mymensingh"};
    ArrayAdapter <String> adapterl;

    EditText name,phone,email,pass,repass;
    Button regi;


    public void init()
    {

        name=(EditText) findViewById(R.id.RegistrationNameText);
        phone=(EditText) findViewById(R.id.RegistrationPhoneText);
        email=(EditText) findViewById(R.id.RegistrationEmailText);
        pass=(EditText) findViewById(R.id.RegistrationPassText);
        repass=(EditText) findViewById(R.id.RegistrationRepassText);

        regi=(Button) findViewById(R.id.RegistrationRegiButton);

        blsp=(Spinner)findViewById(R.id.RegistrationBloodCombo);
        adapterb= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,bType);
        adapterb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blsp.setAdapter(adapterb);
        blsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blsp.setSelection(position);
                regBtype= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lsp=(Spinner)findViewById(R.id.RegistrationLocationCombo);
        adapterl= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,locat);
        adapterl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lsp.setAdapter(adapterl);
        lsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lsp.setSelection(position);
                regLocation= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);
        init();
        openHelper = new DataBaseHelper(this);
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String cName= name.getText().toString();
                String cphone= phone.getText().toString();
                String cemail= email.getText().toString();
                String cpass= pass.getText().toString();
                String crepass= repass.getText().toString();
                if (!cName.isEmpty() && !cphone.isEmpty() && !cemail.isEmpty() && !cpass.isEmpty() && !crepass.isEmpty())
                {
                    SQLiteDatabase db= openHelper.getReadableDatabase();
                    Cursor cursor= db.rawQuery("Select * from Donor where email=?", new String[]{cemail});
                    if(cursor.getCount()>0)
                    {
                        Toast.makeText(DonorRegistration.this,"Email already exits!!!!!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if (cpass.equals(crepass))
                        {
                            db=openHelper.getWritableDatabase();
                            ContentValues contentValues= new ContentValues();
                            contentValues.put(DataBaseHelper.DCOL_2, cName);
                            contentValues.put(DataBaseHelper.DCOL_3, cemail);
                            contentValues.put(DataBaseHelper.DCOL_4, cphone);
                            contentValues.put(DataBaseHelper.DCOL_5, cpass);
                            contentValues.put(DataBaseHelper.DCOL_6, regBtype);
                            contentValues.put(DataBaseHelper.DCOL_7, regLocation);
                            contentValues.put(DataBaseHelper.DCOL_8, "Yes");
                            Toast.makeText(DonorRegistration.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                            db.insert(DataBaseHelper.TABLE_NAME, null, contentValues);
                            Intent intent = new Intent(DonorRegistration.this, DonorInterface.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(DonorRegistration.this,"Password do not match",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(DonorRegistration.this,"Fill all the Informations",Toast.LENGTH_LONG).show();


                }
            }
        });


    }
}
