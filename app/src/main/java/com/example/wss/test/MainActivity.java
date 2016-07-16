package com.example.wss.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        assert textView != null;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        Log.d("wang", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("wang", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("wang", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("wang", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("wang", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("wang", "onDestroy");
    }
}
