package com.example.notifiactionwithactions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "10001" ;
    private final static String CHANNEL_NAME = "Notification";
    private final static String CHANNEL_DESC = "default" ;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager manager = (NotificationManager)getApplicationContext()
                        .getSystemService(Context.NOTIFICATION_SERVICE);


                //Initialize intent for yes button

                Intent intent1 = new Intent(MainActivity.this, MainActivity2.class);
                //Put Extra

                intent1.putExtra("yes", true);

                //Add flags

                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                        |Intent.FLAG_ACTIVITY_NEW_TASK);

                //initialize pending intent
                PendingIntent pendingIntent1 = PendingIntent.getActivity(
                        MainActivity.this, 0, intent1, PendingIntent.FLAG_ONE_SHOT);

                //Initialize intent for no button


                Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
                //Put Extra

                intent2.putExtra("no", false);

                //Add flags

                intent2.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                        |Intent.FLAG_ACTIVITY_NEW_TASK);

                //initialize pending intent

                PendingIntent pendingIntent2 = PendingIntent.getActivity(
                        MainActivity.this, 1, intent2, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        MainActivity.this, CHANNEL_ID);

                builder.setContentTitle("Request");
                builder.setContentText("Are you sure you want accept request ?");
                builder.setSmallIcon(R.drawable.ic_notifications);
                builder.addAction(R.drawable.ic_launcher_foreground, "Yes", pendingIntent1);
                builder.addAction(R.drawable.ic_launcher_foreground, "No", pendingIntent2);
                manager.notify(1, builder.build());
            }
        });
    }
}