
public class prueba extends Thread {
    static int cont = 0;

    public static void incrementar() {
        for (int i = 0; i < 4; i++) {
            cont += 1;
        }
    }

    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> incrementar());
        Thread hilo2 = new Thread(() -> incrementar());
        Thread hilo3 = new Thread(() -> incrementar());
        Thread hilo4 = new Thread(() -> incrementar());

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        System.out.println(cont);
    }

}
