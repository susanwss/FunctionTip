package com.example.wss.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    private ImageView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (ImageView) findViewById(R.id.textView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View imageView = new ImageView(this);
            imageView.setBackgroundResource(R.mipmap.tip_show);
            TipShow tipShow = new TipShow();
            tipShow.mainBottom = true;
            tipShow.setShowViewLocation(true, false, false, true, true);
            tipShow.addTip(this, textView, imageView);
        }
    }
}
