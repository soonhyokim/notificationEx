package com.example.kshyo.notificationex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getTokenButton = (Button) findViewById(R.id.checkTokenButton);
        getTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFirebaseInstanceIDService service = new MyFirebaseInstanceIDService();
                service.onTokenRefresh();
            }
        });


    }


}
