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
import android.widget.TextView;
import android.widget.Toast;

public class DonorProfile extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    TextView pid,pname,pemail,pphone,pbtype,plocation;
    String avail[] = {"Yes","No"};
    ArrayAdapter<String> adapter;
    Spinner avl;
    Button logout,save;
    String pAvail;

    public void init()
    {
        pid=(TextView)findViewById(R.id.ProfileIdText);
        pname=(TextView)findViewById(R.id.ProfileNameText);
        pemail=(TextView)findViewById(R.id.ProfileEmailText);
        pphone=(TextView)findViewById(R.id.ProfilePhoneText);
        pbtype=(TextView)findViewById(R.id.ProfileBTypeText);
        plocation=(TextView)findViewById(R.id.ProfileLocationText);

        String gEmail= getIntent().getStringExtra("Email");
        pemail.setText(gEmail);

        avl=(Spinner)findViewById(R.id.ProfileAvailSpinner);
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,avail);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        avl.setAdapter(adapter);
        avl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                avl.setSelection(position);
                pAvail= parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        logout=(Button)findViewById(R.id.ProfileLogoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opd = new Intent(DonorProfile.this,DonorInterface.class);
                startActivity(opd);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);
        init();
        openHelper=new DataBaseHelper(this);
        this.db = openHelper.getReadableDatabase();
        final String pEmail= pemail.getText().toString();
        Cursor cursor= db.rawQuery("Select * from Donor where email= ?", new String[]{pEmail});

        if (cursor.moveToFirst()) {
            do {
                pname.setText(cursor.getString(1));
                pid.setText(cursor.getString(0));
                pphone.setText(cursor.getString(3));
                pbtype.setText(cursor.getString(5));
                plocation.setText(cursor.getString(6));
                String checkav= cursor.getString(7);
                if(checkav.equals("Yes"))
                {
                    avl.setSelection(0);
                }
                else
                {
                    avl.setSelection(1);
                }

            } while (cursor.moveToNext());


        }
        save=(Button)findViewById(R.id.ProfileSaveButton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db=openHelper.getWritableDatabase();
                ContentValues contentValues= new ContentValues();
                contentValues.put(DataBaseHelper.DCOL_8, pAvail);


                db.update("Donor", contentValues, "email='" + pEmail+ "'", null);
                if(pAvail.equals("Yes"))
                {
                    Toast.makeText(DonorProfile.this,"You will be shown in search",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(DonorProfile.this,"You will not be shown in search",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
