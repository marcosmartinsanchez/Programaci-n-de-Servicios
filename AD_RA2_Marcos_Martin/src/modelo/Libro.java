package modelo;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private double precio;
    private int stock;
    private int idGenero;

    public Libro(int idLibro, String titulo, String autor, double precio, int stock, int idGenero) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
        this.idGenero = idGenero;
    }

    public Libro(String titulo, String autor, double precio, int stock, int idGenero) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
        this.idGenero = idGenero;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
}
