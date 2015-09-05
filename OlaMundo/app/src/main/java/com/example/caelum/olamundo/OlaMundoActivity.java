package com.example.caelum.olamundo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by android5243 on 05/09/15.
 */
public class OlaMundoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ola_mundo);
        Log.i("Ciclo de Vida", "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo de Vida", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de Vida", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de Vida", "onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo de Vida", "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de Vida", "onDestroy");
    }
}
