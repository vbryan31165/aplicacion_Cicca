package com.example.cicaa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cicaa.R;
import com.example.cicaa.model.JWTDecode;
import com.example.cicaa.model.login.LoginPost;
import com.example.cicaa.model.login.LoginRespuesta;
import com.example.cicaa.service.ApiClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {
    TextView link_rec;
    EditText inp_email, inp_pass;
    Button btn_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        link_rec = findViewById(R.id.link_rec);
        btn_l = findViewById(R.id.button);
        inp_email = findViewById(R.id.txt_email);
        inp_pass = findViewById(R.id.txt_pass);


        btn_l.setOnClickListener(view -> verificar());



        link_rec.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), rec_password.class)));




    }
    private void verificar(){
        if(inp_email.getText().toString().isEmpty() || inp_pass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Hay campos vacíos", Toast.LENGTH_SHORT).show();



        }else{
            String email = inp_email.getText().toString().trim();
            String passw = inp_pass.getText().toString().trim();


            LoginPost loginPost = new LoginPost();
            loginPost.setCORREO(email);
            loginPost.setCONTRASENA(passw);
            Call<LoginRespuesta> iniciarsesion = ApiClient.postPeticionIniciarSesion().login(loginPost);

            iniciarsesion.enqueue(new Callback<LoginRespuesta>() {

                @Override
                public void onResponse(@NonNull Call<LoginRespuesta> call, @NonNull Response<LoginRespuesta> response) {
                    if(response.body().getCodigo().equals("ok")){

                        Intent intent = new Intent(login.this, dashboard.class);
                        try {
                            String tokenCode = JWTDecode.decoded(response.body().getToken(),getApplicationContext());
                            JSONObject object= new JSONObject(tokenCode);
                            //Toast.makeText(login.this, " eg: "+ object, Toast.LENGTH_SHORT).show();
                            //.n_identificacion=object.getString("n_identificacion");
                            //.id_cliente=object.getInt("id_cliente");
                            //.id_persona=object.getInt("id_persona");
                            //Log.d("TAG",DatosLoginTokenGlobal.n_identificacion );

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                        finish();
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                        builder.setMessage(response.body().getMessage()).show();
                    }

                }

                @Override
                public void onFailure(@NonNull Call<LoginRespuesta> call, @NonNull Throwable t) {
                    Toast.makeText(login.this, ""+t, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }



}