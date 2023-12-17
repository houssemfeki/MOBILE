package com.example.houssem_feki_mesure_glycemie.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.houssem_feki_mesure_glycemie.model.User;

public class LoginController {
    private static final String SHARED_PREFS = "sharedPrefs";
    private static User user;

    private static LoginController Instance = null;

    private LoginController() {
        super();
    }

    @SuppressLint("SuspiciousIndentation")
    public static final LoginController getInstance(Context context) {
        if (LoginController.Instance == null)
            Instance = new LoginController();
            recapUser(context);
        return LoginController.Instance;
    }
    public static void recapUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password  = sharedPreferences.getString("password","");
        user = new User(username,password);
    }
    public void createUser(String userName, String password , Context context) {
        // Création de l'utilisateur
        user = new User(userName, password);
        // persistance des données de l'utilisateur (Sauvegarde)
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", userName);
        editor.putString("password", password);
        editor.apply();
    }

    public String getUserName(){
        return user.getUserName();
    }
    public String getPassword(){
        return user.getPassword();
    }

}
