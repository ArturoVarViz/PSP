
public class Cuentavocales {
    private static final char[] VOCALS = { 'a', 'e', 'i', 'o', 'u' };
    private int totalVocals = 0;

    public void contarVocales(String texto) {
        Thread[] threads = new Thread[VOCALS.length];
        for (int i = 0; i < VOCALS.length; i++) {
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 0;
                    for (char c : texto.toLowerCase().toCharArray()) {
                        if (c == VOCALS[index]) {
                            count++;
                        }
                    }
                    totalVocals += count;
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getTotalVocals() {
        return totalVocals;
    }
}

class Main {
    public static void main(String[] args) {
        Cuentavocales contador = new Cuentavocales();
        String texto = "perro";
        contador.contarVocales(texto);
        System.out.println("El total de vocales es: " + contador.getTotalVocals());
    }
}
