

import java.util.Random;

public class Ej6 {
    private static final int CAPITAL_INICIAL = 10;
    private int caja = CAPITAL_INICIAL;
    private Random random = new Random();

    public synchronized void ingreso(int cantidad) {
        caja += cantidad;
        System.out.println("Ingreso de: " + cantidad + ", saldo actual: " + caja);
    }

    public synchronized void extraccion(int cantidad) {
        if (caja >= cantidad) {
            caja -= cantidad;
            System.out.println("Extracción de: " + cantidad + ", saldo actual: " + caja);
        } else {
            System.out.println("No se puede extraer: " + cantidad + ", saldo insuficiente.");
        }
    }

    public static class HiloIngresos extends Thread {
        private Ej6 empresa;
        private Random random = new Random();

        public HiloIngresos(Ej6 empresa) {
            this.empresa = empresa;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                empresa.ingreso(random.nextInt(200));
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public static class HiloExtracciones extends Thread {
        private Ej6 empresa;
        private Random random = new Random();

        public HiloExtracciones(Ej6 empresa) {
            this.empresa = empresa;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                empresa.extraccion(random.nextInt(100));
                try { Thread.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }

    public static void main(String[] args) {
        Ej6 empresa = new Ej6();

        HiloIngresos hiloIngresos = new HiloIngresos(empresa);
        HiloExtracciones hiloExtracciones = new HiloExtracciones(empresa);

        hiloIngresos.start();
        hiloExtracciones.start();
    }
}
