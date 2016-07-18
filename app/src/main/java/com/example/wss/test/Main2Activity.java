package com.example.wss.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    private ImageView textView;
    private TipShow tipShow;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (ImageView) findViewById(R.id.textView);

        imageView = new ImageView(this);
        imageView.setImageResource(R.mipmap.tip_show);
        tipShow = new TipShow();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            tipShow.mainBottom = true;
            tipShow.setShowViewLocation(true, false, false, false, true);
            tipShow.addTip(this, textView, imageView);
        }
    }
}
