package com.example.notifiactionwithactions;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView2);

        NotificationManager manager = (NotificationManager)getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        manager.cancelAll();

        if (getIntent().hasExtra("yes")){

            textView.setText("Action is yes");
            textView.setTextColor(Color.GREEN);

        }else if(getIntent().hasExtra("no")){
            textView.setText("Action is no");
            textView.setTextColor(Color.RED);
        }
    }
}