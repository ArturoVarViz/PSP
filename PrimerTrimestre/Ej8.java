import java.util.Random;

class Ascensor extends Thread {
    private int pisoActual = 0;
    private Integer pisoDestino = null;
    private int ultimoPiso = -1;

    public synchronized void llamar(int piso) {
        // Solo cambia el piso destino si el ascensor no está en movimiento
        if (pisoDestino == null && piso != ultimoPiso) {
            pisoDestino = piso;
            notifyAll();
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (pisoDestino != null) {
                    if (pisoActual < pisoDestino) {
                        pisoActual++;
                    } else if (pisoActual > pisoDestino) {
                        pisoActual--;
                    } else {
                        System.out.println("Ascensor ha llegado al piso: " + pisoActual);
                        ultimoPiso = pisoActual;
                        pisoDestino = null;
                    }
                    System.out.println("Ascensor en piso: " + pisoActual);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Ascensor ascensor = new Ascensor();
        ascensor.start();

        Random rand = new Random();
        int ultimoPisoLlamado = -1;
        for (int i = 0; i < 5; i++) {
            int pisoAleatorio;
            do {
                pisoAleatorio = rand.nextInt(11); // Genera un número aleatorio entre 0 y 10
            } while (pisoAleatorio == ultimoPisoLlamado);
            ultimoPisoLlamado = pisoAleatorio;
            ascensor.llamar(pisoAleatorio);
            try {
                Thread.sleep(5000); // Espera 5 segundos antes de llamar al ascensor de nuevo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
