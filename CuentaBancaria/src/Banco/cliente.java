package Banco;

import java.io.Serializable;

public class cliente implements Serializable {
    private String nombre;
    private String dni;

    public cliente(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String toString() {
        return nombre + " (DNI: " + dni + ")";
    }
}
