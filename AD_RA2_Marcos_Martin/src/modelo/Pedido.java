package modelo;

import java.sql.Date;

public class Pedido {
    private int idPedido;
    private int idCliente;
    private Date fecha;

    public Pedido(int idPedido, int idCliente, Date fecha) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
    }

    public Pedido(int idCliente, Date fecha) {
        this.idCliente = idCliente;
        this.fecha = fecha;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
