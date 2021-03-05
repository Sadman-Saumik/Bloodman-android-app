package com.example.turbo.bloodman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button b1;
    public Button b2;


    public void init()
    {
        b1 = (Button)findViewById(R.id.mainDonorButton);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent opd = new Intent(MainActivity.this,DonorInterface.class);
                startActivity(opd);
            }
        });

        b2= (Button)findViewById(R.id.mainReceiverButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opr = new Intent(MainActivity.this,ReceiverInterface.class);
                startActivity(opr);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
