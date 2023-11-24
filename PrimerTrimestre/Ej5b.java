

public class Ej5b {
    public static void main(String[] args) {
        // Creación del primer hilo
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Este es el código que se ejecutará cuando se inicie el hilo
                System.out.println("Hola, soy el hilo número 1");
            }
        });

        // Creación del segundo hilo
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Este es el código que se ejecutará cuando se inicie el hilo
                System.out.println("Hola, soy el hilo número 2");
            }
        });

        // Establecimiento de la prioridad del primer hilo al mínimo
        thread1.setPriority(Thread.MIN_PRIORITY);
        // Establecimiento de la prioridad del segundo hilo al máximo
        thread2.setPriority(Thread.MAX_PRIORITY);

        // Inicio del primer hilo
        thread1.start();
        // Inicio del segundo hilo
        thread2.start();
    }
}

