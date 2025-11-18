
public class Caballo extends Thread {
    public Caballo(String nombre) {
        super(nombre);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " trota... paso " + i);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(getName() + " ha sido interrumpido.");
                return;
            }
        }
        System.out.println(getName() + " ha parado.");
    }
}
