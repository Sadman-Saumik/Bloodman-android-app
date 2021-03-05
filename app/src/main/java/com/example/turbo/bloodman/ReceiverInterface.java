package com.example.turbo.bloodman;

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
import android.widget.ListView;
import android.widget.Spinner;

public class ReceiverInterface extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    String recBtype,recLocation;
    Spinner blsp,lsp;
    Button search;
    ListView rList;
    DataAdapter dataAdapter;

    String bType[] = {"A+","B+","AB+","O+","A-","B-","AB-","O-"};
    ArrayAdapter <String> adapterb;
    String locat[] = {"Dhaka","Rajshahi","Barishal","Khulna","Chattagram","Sylhet","Rangpur","Mymensingh"};
    ArrayAdapter <String> adapterl;
    public void initi()
    {
        blsp=(Spinner)findViewById(R.id.ReceiverBloodSpinner);
        adapterb= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,bType);
        adapterb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blsp.setAdapter(adapterb);
        blsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                blsp.setSelection(position);
                recBtype= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lsp=(Spinner)findViewById(R.id.ReceiverLocationSpinner);
        adapterl= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,locat);
        adapterl.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lsp.setAdapter(adapterl);
        lsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lsp.setSelection(position);
                recLocation= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_interface);
        initi();
        openHelper=new DataBaseHelper(this);
        rList=(ListView)findViewById(R.id.ReceiverList);

        search=(Button)findViewById(R.id.ReceiverSearchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stat="Yes";
                db = openHelper.getReadableDatabase();
                Cursor cursor= db.rawQuery("Select * from Donor where Bloodt=? AND Location=? AND Avail=?", new String[]{recBtype,recLocation,stat});
                dataAdapter=new DataAdapter(getApplicationContext(),R.layout.row_layout);
                rList.setAdapter(dataAdapter);
                if (cursor.moveToFirst()) {
                    do {
                        String name,phone;
                        name=cursor.getString(1);
                        phone=cursor.getString(3);
                        DonorListData donorListData=new DonorListData(name,phone);
                        dataAdapter.add(donorListData);


                    } while (cursor.moveToNext());


                }

            }
        });
    }


}
