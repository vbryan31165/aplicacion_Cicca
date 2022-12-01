package com.example.cicaa.service;

import com.example.cicaa.model.login.LoginPost;
import com.example.cicaa.model.login.LoginRespuesta;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Apilogin {
    @POST("login")
    Call<LoginRespuesta> login(@Body LoginPost loginPost);
}
