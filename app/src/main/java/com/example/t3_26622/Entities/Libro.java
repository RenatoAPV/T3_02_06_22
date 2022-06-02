package com.example.t3_26622.Entities;

public class Libro {
    public int id;
    public String titulo;
    public String resumen;
    public String autor;
    public String fecha;
    public String tienda_1;
    public String tienda_2;
    public String tienda_3;
    public String imagen;

    public Libro() {
    }

    public Libro(int id, String titulo, String resumen, String autor, String fecha, String tienda_1, String tienda_2, String tienda_3, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.resumen = resumen;
        this.autor = autor;
        this.fecha = fecha;
        this.tienda_1 = tienda_1;
        this.tienda_2 = tienda_2;
        this.tienda_3 = tienda_3;
        this.imagen = imagen;
    }
}
