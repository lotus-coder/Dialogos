package com.example.dialogos;


public class Webs {
    private String nombre,url,rutaImagen;
    private int imagen;

    public Webs(String nom,String url,String rutaImg){
        this.nombre = nom;
        this.rutaImagen = rutaImg;
        this.url = url;
    }
    public Webs(String nom,String url,int rutaImg){
        this.nombre = nom;
        this.imagen = rutaImg;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public String toString() {
        return nombre ;
    }
}