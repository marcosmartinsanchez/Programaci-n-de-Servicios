package Banco;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String ARCHIVO = "datos/cuenta.dat";

    public static void main(String[] args) {
        Cuenta cuenta = null;

        File carpeta = new File("datos");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        File archivo = new File(ARCHIVO);
        if (archivo.exists()) {
            cuenta = cargarCuenta();
            if (cuenta == null) {
                System.out.println("No se pudo cargar la cuenta. Se creará una nueva.");
                cuenta = crearCuenta();
            } else {
                System.out.println("Cuenta cargada correctamente.");
            }
        } else {
            cuenta = crearCuenta();
        }

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Ingresar dinero");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Consultar saldo y movimientos");
            System.out.println("4. Exportar movimientos a CSV");
            System.out.println("5. Salir y guardar");
            System.out.print("Elige opción: ");

   
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Entrada inválida. Introduce un número del 1 al 5.");
                opcion = 0; 
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Cantidad a ingresar: ");
                    try {
                        double ingreso = Double.parseDouble(sc.nextLine().trim());
                        cuenta.ingresar(ingreso);
                        System.out.println(" Ingreso realizado.");
                    } catch (NumberFormatException e) {
                        System.out.println(" Entrada inválida. Debes introducir un número válido.");
                    }
                    break;
                case 2:
                    System.out.print("Cantidad a retirar: ");
                    try {
                        double retiro = Double.parseDouble(sc.nextLine().trim());
                        if (cuenta.retirar(retiro)) {
                            System.out.println(" Retirada realizada.");
                        } else {
                            System.out.println(" No tienes suficiente saldo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(" Entrada inválida. Debes introducir un número válido.");
                    }
                    break;
                case 3:
                    System.out.println("Saldo actual: " + cuenta.getSaldo() + " €");
                    System.out.println("Movimientos:");
                    ArrayList<movimiento> movs = cuenta.getMovimientos();
                    for (movimiento m : movs) {
                        System.out.println(m);
                    }
                    break;
                case 4:
                    String rutaCSV = "datos/movimientos.csv";
                    ExportadorCSV.exportarMovimientos(cuenta, rutaCSV);
                    break;
                case 5:
                    guardarCuenta(cuenta);
                    System.out.println(" Datos guardados. ¡Adiós!");
                    break;
                default:
                    System.out.println(" Opción no válida. Introduce un número entre 1 y 5.");
            }
        } while (opcion != 5);

        sc.close();
    }

    private static Cuenta crearCuenta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Creando nueva cuenta...");
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine().trim();
        System.out.print("DNI del cliente: ");
        String dni = sc.nextLine().trim();
        cliente cliente = new cliente(nombre, dni);
        return new Cuenta(cliente);
    }

    private static void guardarCuenta(Cuenta cuenta) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            oos.writeObject(cuenta);
        } catch (IOException e) {
            System.out.println(" Error al guardar la cuenta: " + e.getMessage());
        }
    }

    private static Cuenta cargarCuenta() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (Cuenta) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Error al cargar la cuenta: " + e.getMessage());
            return null;
        }
    }
}

