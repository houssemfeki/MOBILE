package com.example.houssem_feki_mesure_glycemie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.houssem_feki_mesure_glycemie.R;
import com.example.houssem_feki_mesure_glycemie.controller.LoginController;

public class HomeActivity extends AppCompatActivity {
    private Button btnConsultation;
    private EditText etUserName  ;
    private EditText etPassword ;
    private LoginController LoginController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(HomeActivity.this,MainActivity.class);
              startActivity(intent);
              finish();
            }
        });
    }

 private void init(){
    btnConsultation = (Button) findViewById(R.id.btnConsultation);
    LoginController = LoginController.getInstance(HomeActivity.this);
    etPassword = (EditText) findViewById(R.id.etPassword);
    etUserName =  (EditText) findViewById(R.id.etUserName);
 }

}