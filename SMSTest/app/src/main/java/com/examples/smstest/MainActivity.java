package com.examples.smstest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button myButton1, myButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton1 = (Button) findViewById(R.id.button);
        myButton1.setOnClickListener(this);
        myButton2 = (Button) findViewById(R.id.button1);
        myButton2.setOnClickListener(this);
    }
    @Override
    public void onClick (View v) {
        switch (v.getId()){
            case R.id.button:
                Send_SMS();
                break;
            case R.id.button1:
                Start_SMS_App();
                break;
        }
    }

    private void Send_SMS() {
        String phoneNo = "4091234567";
        String message = "Hello, you are you today?";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private void Start_SMS_App() {
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address", new String ("4091234567"));
        smsIntent.putExtra("sms_body", "Test message 2");

        try {
            startActivity(smsIntent);
            finish();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "SMS failed", Toast.LENGTH_SHORT).show();
        }
    }
}
