package com.example.pc.epoka_missions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clic (View view) {
        EditText numero = (EditText) findViewById(R.id.etNumero);
        EditText password = (EditText) findViewById(R.id.etPassword);

        String urlServiceWeb = "http://172.16.47.15/epoka.php?numero=" + numero.getText() +
                "&password=" + password.getText();

    }
}
