import dao.*;
import modelo.*;
import java.sql.Date;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GeneroDao generoDao = new GeneroDao();
        LibroDAO libroDAO = new LibroDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();
        VentaDAO ventaDAO = new VentaDAO();

        Genero genero = new Genero("Ciencia Ficción");
        generoDao.insertarGenero(genero);
        System.out.println("Género insertado con ID: " + genero.getIdGenero());

        Libro libro = new Libro("Dune", "Frank Herbert", 25.99, 10, genero.getIdGenero());
        libroDAO.insertarLibro(libro);
        System.out.println("Libro insertado con ID: " + libro.getIdLibro());

        ArrayList<Libro> libros = libroDAO.listarLibros();
        System.out.println("Listado de libros:");
        for (Libro l : libros) {
            System.out.println(l.getIdLibro() + " - " + l.getTitulo() + " (" + l.getAutor() + ")");
        }

        Cliente cliente = new Cliente("Juan Pérez", "juanperez@mail.com", "Calle Falsa 123");
        clienteDAO.insertarCliente(cliente);
        System.out.println("Cliente insertado con ID: " + cliente.getIdCliente());

        Pedido pedido = new Pedido(cliente.getIdCliente(), new Date(System.currentTimeMillis()));
        pedidoDAO.insertarPedido(pedido);
        System.out.println("Pedido insertado con ID: " + pedido.getIdPedido());

        Venta venta = new Venta(pedido.getIdPedido(), libro.getIdLibro(), 2, libro.getPrecio());
        ventaDAO.insertarVenta(venta);
        System.out.println("Venta insertada para pedido ID " + venta.getIdPedido());

        ArrayList<Cliente> clientes = clienteDAO.listarClientes();
        System.out.println("Listado de clientes:");
        for (Cliente c : clientes) {
            System.out.println(c.getIdCliente() + " - " + c.getNombre() + " - " + c.getEmail());
        }

        ArrayList<Pedido> pedidos = pedidoDAO.listarPedidos();
        System.out.println("Listado de pedidos:");
        for (Pedido p : pedidos) {
            System.out.println(p.getIdPedido() + " - Cliente ID: " + p.getIdCliente() + " - Fecha: " + p.getFecha());
        }

        ArrayList<Venta> ventas = ventaDAO.listarVentas();
        System.out.println("Listado de ventas:");
        for (Venta v : ventas) {
            System.out.println("Pedido ID: " + v.getIdPedido() + ", Libro ID: " + v.getIdLibro() + ", Cantidad: " + v.getCantidad());
        }
    }
}

