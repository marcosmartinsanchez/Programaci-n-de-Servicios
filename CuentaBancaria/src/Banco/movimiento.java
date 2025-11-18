package Banco;

import java.io.Serializable;

public class movimiento implements Serializable {
    private String tipo; 
    private double cantidad;

    public movimiento(String tipo, double cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String toString() {
        return tipo + ": " + cantidad + " €";
    }
}
