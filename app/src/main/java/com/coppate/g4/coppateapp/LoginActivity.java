package com.coppate.g4.coppateapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;        //Botón y volver atrás como atributos
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //Si el inicio de sesion es exitoso
                goMainScreen();

            }
            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.com_facebook_loginview_cancel_action,Toast.LENGTH_SHORT).show(); //Muestra el mensaje de cancelado y vuelvee al login

            }

            @Override
            public void onError(FacebookException error) { //Muestra mensaje de que requiere conexion a internet si es que no hay.
                Toast.makeText(getApplicationContext(),R.string.com_facebook_internet_permission_error_message, Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void goMainScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);  //Unica pantalla en ejecución
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);


    }
}
