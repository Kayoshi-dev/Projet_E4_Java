package com.example.pc.epoka_missions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends Activity{
    int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void onClic(View view) {
        Intent intent = new Intent(getApplicationContext(),ThirdActivity.class);
        Bundle extra = getIntent().getExtras();
        intent.putExtra("no", no);
        startActivity(intent);
        finish();
    }

    public void quitter(View view) {
        finish();
        System.exit(0);
    }

}
