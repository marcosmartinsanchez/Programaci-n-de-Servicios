package Banco;

import java.io.FileWriter;
import java.io.IOException;

public class ExportadorCSV {
    public static void exportarMovimientos(Cuenta cuenta, String rutaArchivo) {
        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            writer.write("Tipo,Cantidad\n");
            for (movimiento m : cuenta.getMovimientos()) {
                writer.write(m.getTipo() + "," + m.getCantidad() + "\n");
            }
            System.out.println("Movimientos exportados correctamente a: " + rutaArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar movimientos: " + e.getMessage());
        }
    }
}
