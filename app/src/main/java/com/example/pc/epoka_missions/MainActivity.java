package com.example.pc.epoka_missions;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clic(View view) {
        EditText numero = (EditText) findViewById(R.id.etNumero);
        EditText password = (EditText) findViewById(R.id.etPassword);


        String urlServiceWeb = "http://172.16.47.15/epoka.php?numero=" + numero.getText() +
                "&password=" + password.getText();


        try {
            if (numero.getText().toString() != "" && password.getText().toString() != "") {
                InputStream is = null;
                URL url = new URL(urlServiceWeb);
                HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
                connexion.connect();
                is = connexion.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
            }
        }
        catch (Exception expt){
            Log.e("log_tag", "Erreur pendant l'authentification" + expt.toString());

        }
    }
}