package superMercado;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SuperMarket {
    public int M; // Número de clientes
    public AtomicInteger[] cajas; // Array para representar las cajas
    private double[] pagoTotal; // Array para almacenar el total pagado

    // Constructor de la clase SuperMarket
    public SuperMarket(int m, int n) {
        M = m; // Asigna el número de clientes
        this.cajas = new AtomicInteger[n]; // Inicializa las cajas
        for (int i = 0; i < n; i++) {
            this.cajas[i] = new AtomicInteger(0);
        }
        this.pagoTotal = new double[1]; // Inicializa el total pagado
    }

    // Método para abrir el supermercado y comenzar a atender a los clientes
    public void abrir() {
        Thread[] clientes = new Thread[M]; // Crea un array para almacenar los hilos de los clientes
        for (int i = 0; i < M; i++) {
            clientes[i] = new Cliente(cajas, pagoTotal); // Crea un nuevo cliente y lo almacena en el array
            clientes[i].start(); // Inicia el hilo del cliente
        }

        // Espera a que todos los clientes hayan terminado
        for (int i = 0; i < M; i++) {
            try {
                clientes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener el total pagado
    public double getPagoTotal() {
        return pagoTotal[0];
    }

    // Método principal para ejecutar el programa
    public static void main(String[] args) {
        int M = 100; // Número de clientes
        int N = 2; // Número de cajas

        SuperMarket superMarket = new SuperMarket(M, N); // Crea un nuevo supermercado

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (M > 50) {
                    superMarket.cajas = new AtomicInteger[3];
                    for (int i = 0; i < 3; i++) {
                        superMarket.cajas[i] = new AtomicInteger(0);
                    }
                    System.out.println("Se ha abierto una nueva caja");
                }
                if (M > 100) {
                    superMarket.cajas = new AtomicInteger[4];
                    for (int i = 0; i < 4; i++) {
                        superMarket.cajas[i] = new AtomicInteger(0);
                    }
                    System.out.println("Se ha abierto una nueva caja");
                }
            }
        }, 4000);

        superMarket.abrir(); // Abre el supermercado

        // Imprime el total pagado
        System.out.println("Total pagado: " + superMarket.getPagoTotal());
    }
}
