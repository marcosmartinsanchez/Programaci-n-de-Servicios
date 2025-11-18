
public class Principal {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Por favor, introduce tres nombres de caballos como argumentos.");
            System.exit(1);
        }

        Vigilante vigilante = new Vigilante();
        vigilante.start();

        Caballo c1 = new Caballo(args[0]);
        Caballo c2 = new Caballo(args[1]);
        Caballo c3 = new Caballo(args[2]);

        c1.start();
        c2.start();
        c3.start();

        try {
            c1.join();
            c2.join();
            c3.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido.");
        }

        System.out.println("Todos los caballos han terminado. Fin del programa.");
    }
}
