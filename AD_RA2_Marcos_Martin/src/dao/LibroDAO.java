package dao;

import conexion.ConexionBD;
import modelo.Libro;

import java.sql.*;
import java.util.ArrayList;

public class LibroDAO {

    public void insertarLibro(Libro libro) {
        String sql = "INSERT INTO Libro (titulo, autor, precio, stock, id_genero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDouble(3, libro.getPrecio());
            stmt.setInt(4, libro.getStock());
            stmt.setInt(5, libro.getIdGenero());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                libro.setIdLibro(rs.getInt(1));
            }

            System.out.println("Libro insertado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar libro: " + e.getMessage());
        }
    }

    public ArrayList<Libro> listarLibros() {
        ArrayList<Libro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Libro";

        try (Connection conn = ConexionBD.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("id_genero")
                );
                lista.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }

        return lista;
    }

    public void actualizarLibro(Libro libro) {
        String sql = "UPDATE Libro SET titulo = ?, autor = ?, precio = ?, stock = ?, id_genero = ? WHERE id_libro = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setDouble(3, libro.getPrecio());
            stmt.setInt(4, libro.getStock());
            stmt.setInt(5, libro.getIdGenero());
            stmt.setInt(6, libro.getIdLibro());

            stmt.executeUpdate();
            System.out.println("Libro actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(int idLibro) {
        String sql = "DELETE FROM Libro WHERE id_libro = ?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLibro);
            stmt.executeUpdate();
            System.out.println("Libro eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar libro: " + e.getMessage());
        }
    }

    public Libro obtenerLibroPorId(int idLibro) {
        String sql = "SELECT * FROM Libro WHERE id_libro = ?";
        Libro libro = null;

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLibro);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                libro = new Libro(
                        rs.getInt("id_libro"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("id_genero")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener libro: " + e.getMessage());
        }

        return libro;
    }
}
