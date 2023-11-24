package superMercado;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Cliente extends Thread {
    private final AtomicInteger[] cajas;
    private double[] pagoTotal;

    public Cliente(AtomicInteger[] cajas, double[] pagoTotal) {
        this.cajas = cajas;
        this.pagoTotal = pagoTotal;
    }

    @Override
    public void run() {
        try {
            // El cliente está comprando
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 15000));

            // El cliente elige una caja y se pone en la cola
            int caja = seleccionarCaja();

            // El cliente realiza el pago
            double pago = ThreadLocalRandom.current().nextDouble(100);

            synchronized (cajas[caja]) {
                cajas[caja].incrementAndGet();
                pagoTotal[0] += pago;
            }

            System.out.println("Cliente " + this.getId() + " pagó: " + pago + " en la caja " + caja);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int seleccionarCaja() {
        int cajaSeleccionada = 0;
        for (int i = 1; i < cajas.length; i++) {
            if (cajas[i].get() < cajas[cajaSeleccionada].get()) {
                cajaSeleccionada = i;
            }
        }
        return cajaSeleccionada;
    }
}
