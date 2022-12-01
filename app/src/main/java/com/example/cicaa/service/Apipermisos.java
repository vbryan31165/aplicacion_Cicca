package com.example.cicaa.service;

import com.example.cicaa.model.Permisos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Apipermisos {

    @GET("permisos_usuario/{id_usuario}")
    Call<List<Permisos>> getPermiso(@Path("id_usuario") int id_usuario);
}
