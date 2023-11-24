// La clase Buzon hereda de Thread
class Buzon extends Thread {
    // Variable para almacenar el mensaje en el buzón
    private String mensaje = null;
    // Variable para determinar si el hilo es un escritor
    private final boolean esEscritor;

    // Constructor de la clase Buzon
    public Buzon(boolean esEscritor) {
        this.esEscritor = esEscritor;
    }

    // Método para enviar un mensaje al buzón
    public synchronized void enviar(String mensaje) throws InterruptedException {
        // Espera mientras el buzón esté lleno
        while (this.mensaje != null) {
            wait();
        }
        // Almacena el mensaje en el buzón
        this.mensaje = mensaje;
        // Notifica a todos los hilos que están esperando
        notifyAll();
    }

    // Método para leer un mensaje del buzón
    public synchronized String leer() throws InterruptedException {
        // Espera mientras el buzón esté vacío
        while (this.mensaje == null) {
            wait();
        }
        // Lee el mensaje del buzón
        String mensaje = this.mensaje;
        // Vacía el buzón
        this.mensaje = null;
        // Notifica a todos los hilos que están esperando
        notifyAll();
        // Devuelve el mensaje leído
        return mensaje;
    }

    // Método run que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        try {
            // Si el hilo es un escritor
            if (esEscritor) {
                int i = 0;
                // Envia mensajes al buzón en un bucle infinito
                while (true) {
                    enviar("Mensaje " + i);
                    i++;
                }
            } else {
                // Si el hilo es un lector
                while (true) {
                    // Lee mensajes del buzón en un bucle infinito
                    String mensaje = leer();
                    System.out.println("Lector leyo: " + mensaje);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Método main para probar la clase Buzon
    public static void main(String[] args) {
        // Crea un hilo Buzon para leer mensajes
        Buzon buzonLector = new Buzon(false);
        // Crea un hilo Buzon para escribir mensajes
        Buzon buzonEscritor = new Buzon(true);
        // Inicia los hilos
        buzonLector.start();
        buzonEscritor.start();
    }
}
