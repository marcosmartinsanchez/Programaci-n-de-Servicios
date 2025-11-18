import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Leercsv {
    public static void main(String[] args) {
        String archivo = "productos.txt";

        File file = new File(archivo);
        if (file.exists()) {
            long fileSize = file.length();
            System.out.println("El archivo ocupa " + fileSize + " bytes.");

            int totalArticulos = 0;
            double sumaPrecios = 0;
            double sumaImporteTotal = 0;

            List<String[]> productos = new ArrayList<>();

            try {
                BufferedReader reader = Files.newBufferedReader(Paths.get(archivo));
                String linea;
                
                while ((linea = reader.readLine()) != null) {
 
                    String[] datos = linea.split(";");
                    productos.add(datos);

                    String categoria = datos[0];
                    String nombre = datos[1];
                    double precio = Double.parseDouble(datos[2]);
                    int stock = Integer.parseInt(datos[3]);

                    System.out.printf("%s (%s) -- Precio: %.2f €  -- Stock: %d%n", nombre, categoria, precio, stock);

                    totalArticulos++;
                    sumaPrecios += precio;
                    sumaImporteTotal += precio * stock;
                }

                reader.close();

                double promedioPrecios = totalArticulos > 0 ? sumaPrecios / totalArticulos : 0;

                System.out.println("\nResumen final:");
                System.out.println("Número total de artículos: " + totalArticulos);
                System.out.printf("Promedio de precios: %.2f €%n", promedioPrecios);
                System.out.printf("Importe total: %.2f €%n", sumaImporteTotal);

            } catch (IOException e) {
                System.out.println("Error al leer el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}
