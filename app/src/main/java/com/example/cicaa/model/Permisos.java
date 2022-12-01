package com.example.cicaa.model;

public class Permisos {
    /*{
        "APELLIDOS": "perez",
        "ID_PERMISO": "1",
        "ID_SALON": "4",
        "NOMBRES": "maria",
        "PERMISO": "0"
    }*/

    private String APELLIDOS;
    private String ID_PERMISO;
    private String ID_SALON;
    private String NOMBRES;
    private String PERMISO;

    public Permisos(String APELLIDOS, String ID_PERMISO, String ID_SALON, String NOMBRES, String PERMISO) {
        this.APELLIDOS = APELLIDOS;
        this.ID_PERMISO = ID_PERMISO;
        this.ID_SALON = ID_SALON;
        this.NOMBRES = NOMBRES;
        this.PERMISO = PERMISO;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public String getID_PERMISO() {
        return ID_PERMISO;
    }

    public String getID_SALON() {
        return ID_SALON;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public String getPERMISO() {
        return PERMISO;
    }
}
