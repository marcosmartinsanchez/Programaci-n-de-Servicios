package dao;

import conexion.ConexionBD;
import modelo.Pedido;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDAO {

    public void insertarPedido(Pedido pedido) {
        String sql = "INSERT INTO Pedido (id_cliente, fecha) VALUES (?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setDate(2, pedido.getFecha());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setIdPedido(rs.getInt(1));
                }
            }
            System.out.println("Pedido insertado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar pedido: " + e.getMessage());
        }
    }

    public ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id_pedido"),
                        rs.getInt("id_cliente"),
                        rs.getDate("fecha"));
                lista.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarPedido(Pedido pedido) {
        String sql = "UPDATE Pedido SET id_cliente = ?, fecha = ? WHERE id_pedido = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedido.getIdCliente());
            stmt.setDate(2, pedido.getFecha());
            stmt.setInt(3, pedido.getIdPedido());
            stmt.executeUpdate();
            System.out.println("Pedido actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
        }
    }

    public void eliminarPedido(int idPedido) {
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
            System.out.println("Pedido eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
        }
    }
}
