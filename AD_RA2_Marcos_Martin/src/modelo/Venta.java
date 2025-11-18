package modelo;

public class Venta {
    private int idPedido;
    private int idLibro;
    private int cantidad;
    private double precioUnitario;

    public Venta(int idPedido, int idLibro, int cantidad, double precioUnitario) {
        this.idPedido = idPedido;
        this.idLibro = idLibro;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
