package com.lulu.lin.mac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BesierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besier);
        Log.d("abcd","oncreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("abcd","onPause");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("abcd","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("abcd","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("abcd","onDestroy");
    }
}
