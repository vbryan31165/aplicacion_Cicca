package com.example.cicaa.service;

import com.example.cicaa.model.Usuario;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiusuarios {

    @GET("usuarios")
    Call<List<Usuario>> getUsuarios();

    /*@GET("personas/{id_persona}")
    Call<Personas> getPersona(@Path("id_persona") int id_persona);*/
}
