class Buzon extends Thread {
    private String mensaje = null;
    private final boolean esEscritor;

    public Buzon(boolean esEscritor) {
        this.esEscritor = esEscritor;
    }

    public synchronized void enviar(String mensaje) throws InterruptedException {
        while (this.mensaje != null) {
            wait();
        }
        this.mensaje = mensaje;
        notifyAll();
    }

    public synchronized String leer() throws InterruptedException {
        while (this.mensaje == null) {
            wait();
        }
        String mensaje = this.mensaje;
        this.mensaje = null;
        notifyAll();
        return mensaje;
    }

    @Override
    public void run() {
        try {
            if (esEscritor) {
                int i = 0;
                while (true) {
                    enviar("Mensaje " + i);
                    i++;
                }
            } else {
                while (true) {
                    String mensaje = leer();
                    System.out.println("Lector leyo: " + mensaje);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Buzon buzonLector = new Buzon(false);
        Buzon buzonEscritor = new Buzon(true);
        buzonLector.start();
        buzonEscritor.start();
    }
}
