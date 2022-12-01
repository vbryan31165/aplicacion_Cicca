package com.example.cicaa.model;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class JWTDecode {
    public static String decoded(String JWTEncoded, Context context) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");

            return getJson(split[1]);
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(context, "Error al decodificar el token", Toast.LENGTH_SHORT).show();
        }
        return "error";
    }
    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}
