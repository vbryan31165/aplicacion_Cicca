package com.example.cicaa.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.20.55:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Apilogin postPeticionIniciarSesion(){
        return retrofit().create(Apilogin.class);
    }

    public static Apiusuarios getPeticionUsuarios() { return retrofit().create(Apiusuarios.class); }

    public static Apipermisos getPeticionPermisos(){ return retrofit().create(Apipermisos.class); }
}
