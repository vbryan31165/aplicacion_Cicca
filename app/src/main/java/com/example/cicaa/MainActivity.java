package com.example.cicaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


public class MainActivity extends Activity {

    ImageView logo_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo_n=(ImageView) findViewById(R.id.logo_name);


        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), login.class));
            finish();
        }, 3000);

    }
}