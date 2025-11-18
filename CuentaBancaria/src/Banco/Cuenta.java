package Banco;

import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable {
    private cliente cliente;
    private ArrayList<movimiento> movimientos;
    private double saldo;

    public Cuenta(cliente cliente) {
        this.cliente = cliente;
        this.movimientos = new ArrayList<>();
        this.saldo = 0;
    }

    public cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public ArrayList<movimiento> getMovimientos() {
        return movimientos;
    }

    public void ingresar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add(new movimiento("Ingreso", cantidad));
        }
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            movimientos.add(new movimiento("Retirada", cantidad));
            return true;
        }
        return false;
    }
}

