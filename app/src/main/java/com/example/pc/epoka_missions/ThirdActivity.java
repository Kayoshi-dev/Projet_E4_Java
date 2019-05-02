package com.example.pc.epoka_missions;

        import android.app.Activity;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class ThirdActivity extends Activity {
    Spinner ville;
    TextView msg;
    int numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Bundle extras = getIntent().getExtras();
        numero = extras.getInt("no");
        ville = findViewById(R.id.spinner);
        msg = findViewById(R.id.msgErr);
        SetVilleComboBox();
    }

    class ComboBoxItem {
        private int id;
        private String libelle;

        public ComboBoxItem(int unId, String unLibelle) {
            this.id = unId;
            this.libelle = unLibelle;


        }

        @Override
        public String toString() {
            return this.libelle;
        }

        public int toInt() {

            return this.id;
        }
    }

    public void SetVilleComboBox() {
        try {
            // Autorisation des opérations sur le THREAD Principal
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            // Création de l'URL où envoyer la requête
            URL url = new URL("http://172.16.47.16/Projet_E4_Web/ville.php");
            HttpURLConnection cnx = (HttpURLConnection) url.openConnection();
            cnx.connect();
            InputStream is = cnx.getInputStream();
            String txt;
            txt = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
            cnx.disconnect();
            JSONArray jsonArray = new JSONArray(txt);
            List<ComboBoxItem> list = new ArrayList<ComboBoxItem>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(new ComboBoxItem(Integer.parseInt(jsonObject.getString("Ville_Id")), jsonObject.getString("Ville_Nom") + " (" + jsonObject.getString("Ville_CP") + ")"));
            }
            ArrayAdapter<ComboBoxItem> adapter = new ArrayAdapter<ComboBoxItem>(this, R.layout.spinner_item, list);
            ville.setAdapter(adapter);
        } catch (Exception expt) {
            Toast.makeText(this, "Une erreur s'est produite", Toast.LENGTH_LONG).show();
            TextView msg = findViewById(R.id.msgErr);
            msg.setText(expt.getMessage());
        }
    }

    public void ajout(View view) {
        try {

            // champs != vide
            EditText etDeb = findViewById(R.id.etDateDeb);
            EditText etFin = findViewById(R.id.etDateFin);

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String urlSW = "http://172.16.47.16/Projet_E4_Web/ajout.php?dateDeb="+ etDeb.getText()+"&dateFin="+ etFin.getText()+"&ville="+((ComboBoxItem)ville.getSelectedItem()).toInt()+"&idEm="+numero;
            URL url = new URL(urlSW);
            HttpURLConnection connexion = (HttpURLConnection)url.openConnection();
            connexion.connect();
            InputStream is = null;
            is=connexion.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ligne = br.readLine();
            msg.setText(ligne);

        } catch (Exception e){
            msg.setText(e.getMessage());
        }
    }
}
