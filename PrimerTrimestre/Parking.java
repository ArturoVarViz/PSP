import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Parking {
    private final int numPrazas;
    private final int[] prazas;
    private final Semaphore semaphore;

    public Parking(int numPrazas, int numCoches) {
        this.numPrazas = numPrazas;
        this.prazas = new int[numPrazas];
        this.semaphore = new Semaphore(numPrazas, true);

        for (int i = 1; i <= numCoches; i++) {
            new Coche(i, this).start();
        }
    }

    public void aparcar(Coche coche) {
        try {
            while (true) {
                semaphore.acquire();
                synchronized (this) {
                    int praza = -1;
                    for (int i = 0; i < numPrazas; i++) {
                        if (prazas[i] == 0) {
                            prazas[i] = coche.getCocheId();
                            praza = i;
                            break;
                        }
                    }
                    System.out.println("ENTRADA: Coche " + coche.getCocheId() + " aparca en " + praza + ".");
                    System.out.println("Prazas libres: " + semaphore.availablePermits());
                    System.out.println("Parking: " + java.util.Arrays.toString(prazas));
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                synchronized (this) {
                    for (int i = 0; i < numPrazas; i++) {
                        if (prazas[i] == coche.getCocheId()) {
                            prazas[i] = 0;
                            break;
                        }
                    }
                    System.out.println("SAÍDA: Coche " + coche.getCocheId() + " salindo.");
                    System.out.println("Prazas libres: " + semaphore.availablePermits());
                    System.out.println("Parking: " + java.util.Arrays.toString(prazas));
                }
                semaphore.release();
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        int numPrazas = 6;
        int numCoches = 10;
        new Parking(numPrazas, numCoches);
    }
}

class Coche extends Thread {
    private final int cocheId;
    private final Parking parking;

    public Coche(int cocheId, Parking parking) {
        this.cocheId = cocheId;
        this.parking = parking;
    }

    public int getCocheId() {
        return cocheId;
    }

    @Override
    public void run() {
        parking.aparcar(this);
    }
}
