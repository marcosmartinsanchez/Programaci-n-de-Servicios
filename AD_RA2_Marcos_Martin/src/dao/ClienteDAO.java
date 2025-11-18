package dao;

import conexion.ConexionBD;
import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDAO {

    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre, email, direccion) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getDireccion());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                }
            }
            System.out.println("Cliente insertado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    public ArrayList<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("direccion"));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nombre = ?, email = ?, direccion = ? WHERE id_cliente = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getDireccion());
            stmt.setInt(4, cliente.getIdCliente());
            stmt.executeUpdate();
            System.out.println("Cliente actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int idCliente) {
        String sql = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
            System.out.println("Cliente eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
