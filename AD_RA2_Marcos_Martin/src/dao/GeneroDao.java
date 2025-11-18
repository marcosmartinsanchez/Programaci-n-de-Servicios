package dao;


import java.sql.*;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Genero;

public class GeneroDao {

    public void insertarGenero(Genero genero) {
        String sql = "INSERT INTO Genero (nombre) VALUES (?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, genero.getNombre());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    genero.setIdGenero(rs.getInt(1));
                }
            }
            System.out.println("Genero insertado correctamente: " + genero.getNombre());
        } catch (SQLException e) {
            System.out.println("Error al insertar género: " + e.getMessage());
        }
    }

    public ArrayList<Genero> listarGeneros() {
        ArrayList<Genero> lista = new ArrayList<>();
        String sql = "SELECT * FROM Genero";
        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Genero genero = new Genero(rs.getInt("id_genero"), rs.getString("nombre"));
                lista.add(genero);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar géneros: " + e.getMessage());
        }
        return lista;
    }

    public void actualizarGenero(Genero genero) {
        String sql = "UPDATE Genero SET nombre = ? WHERE id_genero = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, genero.getNombre());
            stmt.setInt(2, genero.getIdGenero());
            stmt.executeUpdate();
            System.out.println("Género actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar género: " + e.getMessage());
        }
    }

    public void eliminarGenero(int idGenero) {
        String sql = "DELETE FROM Genero WHERE id_genero = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idGenero);
            stmt.executeUpdate();
            System.out.println("Género eliminado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar género: " + e.getMessage());
        }
    }
}
