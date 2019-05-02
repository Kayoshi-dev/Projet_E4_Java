package com.example.pc.epoka_missions;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
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
        Button connexion = findViewById(R.id.btnConnexion);
    }

    public void connexion(View view) {
        EditText no = (EditText) findViewById(R.id.etNumero);
        EditText password = (EditText) findViewById(R.id.etPassword);
        String urlServiceWeb;


       urlServiceWeb = "http://172.16.47.16/Projet_E4_Web/connexion.php?Sal_Id=" + no.getText() +
                "&Sal_Pwd=" + password.getText();

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            if (no.getText().toString() != "" && password.getText().toString() != "") {
                    InputStream is = null;
                    URL url = new URL(urlServiceWeb);
                    HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
                    connexion.connect();
                    is = connexion.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String ligne = br.readLine();

                    try {
                    int nb = Integer.parseInt(ligne);    // Erreur provenant de cette ligne. Le int n'est pas recupéré
                    Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("no", nb);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e2){
                    Log.e("log_tag", "Votre login n'est pas correct" + e2.toString());

                }
            }
        } catch (Exception expt){
            Log.e("log_tag", "Erreur pendant l'authentification" + expt.toString());
        }
    }
}