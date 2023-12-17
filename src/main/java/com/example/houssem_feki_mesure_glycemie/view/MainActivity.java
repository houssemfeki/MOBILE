package com.example.houssem_feki_mesure_glycemie.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.houssem_feki_mesure_glycemie.R;
import com.example.houssem_feki_mesure_glycemie.controller.Controller;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 1 ;
    private EditText etValeur;
    private Button btnConsulter;
    private RadioButton rbtoui;
    private RadioButton rbtnon;
    private SeekBar sbAge;
    private TextView tvage ; //tvresultat;
    //L'instance de la classe Controller.
    private Controller controller  = Controller.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //seakbar Listnear explicite

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialisation des éléments de l'interface utilisateur
        init();
        // Ajout d'un écouteur de clic sur le bouton Consulter
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Controle de saisie
                int age;
                float valeurmasuree;
                boolean verifage = false;
                boolean verifvaleur = false;
                if (sbAge.getProgress() != 0) {
                    verifage = true;
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez vérifier votre age", Toast.LENGTH_SHORT).show();
                }
                if (!etValeur.getText().toString().isEmpty()) {
                    verifvaleur = true;
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez vérifier la valeur mesurée", Toast.LENGTH_LONG).show();
                }
                if (verifage && verifvaleur) {
                    //Traitement
                    age = sbAge.getProgress();
                    valeurmasuree = Float.valueOf(etValeur.getText().toString());
                    boolean isFasting = rbtoui.isChecked();
                    // User Action view -> controller
                    controller.createPatient(age ,valeurmasuree , isFasting , isChild());

                   // tvresultat.setText(controller.getRes());
                    Intent intent = new Intent(MainActivity.this,ConsultActivity.class);
                    intent.putExtra("reponse",controller.getRes());
                    startActivityForResult(intent , REQUEST_CODE);


            }}
        });

        // Ajout d'un écouteur de changement de progression sur la SeekBar sbAge
        //Seakbar Listnear implicite
        sbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                             @Override
                                             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                 Log.i("Information", "onProgressChanged " + progress);
                                                 // Affichage dans le Log de Android studio pour voir les changements détectés sur le SeekBar ..
                                                 tvage.setText("Votre âge : " + progress);
                                                 // Mise à jour du TextView par la valeur : progress à chaque changement.

                                             }

                                             @Override
                                             public void onStartTrackingTouch(SeekBar seekBar) {
                                             }

                                             @Override
                                             public void onStopTrackingTouch(SeekBar seekBar) {
                                             }
                                         }
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE)
            if(resultCode==RESULT_CANCELED)
                Toast.makeText(MainActivity.this,"Erreur",Toast.LENGTH_SHORT).show();
    }

    /*public void init()
    {
        etValeur = (EditText) findViewById(R.id.edittext);
        sbAge = (SeekBar) findViewById(R.id.sbAge);
        btnConsulter =(Button) findViewById(R.id.btnconsulter);
        rbIsFasting =(RadioButton) findViewById(R.id.rbtoui);
        rbIsNotFasting =(RadioButton) findViewById(R.id.rbtnon);
        tvAge =(TextView) findViewById(R.id.tvAge);
        //tvres = (TextView) findViewById(R.id.btnconsulter);
        Intent intent=new Intent(MainActivity.this,ConsulteActivity.class);
        intent.putExtra("réponse",controller.getResult());

    }
*/
    // Initialisation des éléments de l'interface utilisateur
    private void init() {
        etValeur =(EditText) findViewById(R.id.edittext);
        btnConsulter =(Button) findViewById(R.id.btnConsulter);
        rbtoui = (RadioButton)findViewById(R.id.rbtoui);
        rbtnon= (RadioButton)findViewById(R.id.rbtnon);
        sbAge= (SeekBar)findViewById(R.id.sbAge);
        tvage= (TextView)findViewById(R.id.tvage);
        //tvresultat=(TextView) findViewById(R.id.tvresultat);
        Intent intent = new Intent(MainActivity.this , ConsultActivity.class);
        intent.putExtra("reponse" , controller.getRes());

    }
}