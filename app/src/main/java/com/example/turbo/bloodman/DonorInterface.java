package com.example.turbo.bloodman;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DonorInterface extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    public EditText demail,dpass;
    public Button back;
    public Button registration,login;


    public void init()
    {
        demail=(EditText)findViewById(R.id.DonorEmailText);
        dpass=(EditText)findViewById(R.id.DonorPassText);
        back = (Button)findViewById(R.id.DonorBackButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opm = new Intent(DonorInterface.this,MainActivity.class);
                startActivity(opm);
            }
        });

        registration = (Button)findViewById(R.id.DonorRegistrationButton);
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opr = new Intent(DonorInterface.this,DonorRegistration.class);
                startActivity(opr);
            }
        });


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_interface);
        init();

        openHelper=new DataBaseHelper(this);
        db= openHelper.getReadableDatabase();


        login = (Button)findViewById(R.id.DonorLoginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dcemail=demail.getText().toString();
                String dcpass=dpass.getText().toString();

                cursor= db.rawQuery("Select * from Donor where email=? AND Pass=?", new String[]{dcemail,dcpass});
                if(cursor!=null)
                {
                    if(cursor.getCount()>0)
                    {
                        Intent opl = new Intent(DonorInterface.this,DonorProfile.class);
                        opl.putExtra("Email", demail.getText().toString());
                        startActivity(opl);
                    }
                    else
                    {
                        Toast.makeText(DonorInterface.this,"Email Or Password wrong",Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
}
