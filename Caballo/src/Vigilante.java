
public class Vigilante extends Thread {
    public Vigilante() {
        setDaemon(true); 
    }

    @Override
    public void run() {
        int contador = 1;
        while (true) {
            System.out.println("Tarea de vigilancia nº " + contador++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Vigilante interrumpido.");
                return;
            }
        }
    }
}
