package com.example.cicaa.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String APELLIDOS;
    private String CEDULA;
    private String CORREO;
    private int ID_ROL;
    private int ID_USUARIO;
    private String NOMBRES;

    public Usuario(int ID_USUARIO, String CEDULA, String NOMBRES, String APELLIDOS, String CORREO, int ID_ROL) {
        this.ID_USUARIO = ID_USUARIO;
        this.CEDULA = CEDULA;
        this.NOMBRES = NOMBRES;
        this.APELLIDOS = APELLIDOS;
        this.CORREO = CORREO;
        this.ID_ROL = ID_ROL;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    public void setCEDULA(String CEDULA) {
        this.CEDULA = CEDULA;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public void setID_ROL(int ID_ROL) {
        this.ID_ROL = ID_ROL;
    }

    public void setID_USUARIO(int ID_USUARIO) {
        this.ID_USUARIO = ID_USUARIO;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public int getID_USUARIO() {
        return ID_USUARIO;
    }

    public String getCEDULA() {
        return CEDULA;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public String getCORREO() {
        return CORREO;
    }

    public int getID_ROL() {
        return ID_ROL;
    }
}
