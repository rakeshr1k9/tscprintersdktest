package com.example.myprintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.tscdll.TscWifiActivity;

public class MainActivity extends AppCompatActivity {

    TscWifiActivity TscEthernetDll = new TscWifiActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button test = (Button) findViewById(R.id.button1);

        test.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {

                TscEthernetDll.openport("10.0.10.210",9100,50);

                //String status = TscEthernetDll.printerstatus(300);

                TscEthernetDll.sendcommand("SIZE 75 mm, 50 mm\r\n");
                //TscEthernetDll.sendcommand("GAP 2 mm, 0 mm\r\n");//Gap media
                //TscEthernetDll.sendcommand("BLINE 2 mm, 0 mm\r\n");//blackmark media
                TscEthernetDll.clearbuffer();
                TscEthernetDll.sendcommand("SPEED 4\r\n");
                TscEthernetDll.sendcommand("DENSITY 12\r\n");
                TscEthernetDll.sendcommand("CODEPAGE UTF-8\r\n");
                TscEthernetDll.sendcommand("SET TEAR ON\r\n");
                TscEthernetDll.sendcommand("SET COUNTER @1 1\r\n");
                TscEthernetDll.sendcommand("@1 = \"0001\"\r\n");
                TscEthernetDll.sendcommand("TEXT 100,300,\"ROMAN.TTF\",0,12,12,@1\r\n");
                TscEthernetDll.sendcommand("TEXT 100,400,\"ROMAN.TTF\",0,12,12,\"TEST FONT\"\r\n");
                TscEthernetDll.barcode(100, 100, "128", 100, 1, 0, 3, 3, "123456789");
                TscEthernetDll.printerfont(100, 250, "3", 0, 1, 1, "987654321");
                TscEthernetDll.printlabel(2, 1);

                TscEthernetDll.closeport(5000);
            }

        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
