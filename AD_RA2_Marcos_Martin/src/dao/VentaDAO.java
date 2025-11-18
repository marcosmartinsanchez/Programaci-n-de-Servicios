package dao;

import conexion.ConexionBD;
import modelo.Venta;

import java.sql.*;
import java.util.ArrayList;

public class VentaDAO {

    public void insertarVenta(Venta venta) {
        String sql = "INSERT INTO Venta (id_pedido, id_libro, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venta.getIdPedido());
            stmt.setInt(2, venta.getIdLibro());
            stmt.setInt(3, venta.getCantidad());
            stmt.setDouble(4, venta.getPrecioUnitario());
            stmt.executeUpdate();
            System.out.println("Venta insertada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar venta: " + e.getMessage());
        }
    }

    public ArrayList<Venta> listarVentas() {
        ArrayList<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Venta";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("id_pedido"),
                        rs.getInt("id_libro"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_unitario"));
                lista.add(venta);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarVenta(Venta venta) {
        String sql = "UPDATE Venta SET cantidad = ?, precio_unitario = ? WHERE id_pedido = ? AND id_libro = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, venta.getCantidad());
            stmt.setDouble(2, venta.getPrecioUnitario());
            stmt.setInt(3, venta.getIdPedido());
            stmt.setInt(4, venta.getIdLibro());
            stmt.executeUpdate();
            System.out.println("Venta actualizada correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar venta: " + e.getMessage());
        }
    }
}
