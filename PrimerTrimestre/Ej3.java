
public class Ej3 extends Thread {
    private int n; // Variable para almacenar el número de hilos.

    public Ej3(String name, int n) { // Constructor de la clase.
        super(name); // Llama al constructor de la clase padre Thread, estableciendo el nombre del hilo.
        this.n = n; // Inicializa la variable n.
    }

    @Override
    public void run() { // Método que se ejecuta cuando se inicia el hilo.
        System.out.println(getName() + " comenzó."); // Imprime un mensaje indicando que el hilo ha comenzado.

        Ej3 t = null; // Inicializa una variable de tipo Ej3 (que es un hilo).

        if (n > 0) { // Si n es mayor que 0, crea un nuevo hilo.
            t = new Ej3("Hilo " + (n-1), n-1); // Crea un nuevo hilo con el nombre "Hilo" seguido del valor de n-1 y lo asigna a la variable t.
            t.start(); // Inicia el nuevo hilo.
        }

        for (int i = 0; i < 10; i++) { // Bucle que se ejecuta 10 veces.
            System.out.println(getName() + " se está procesando."); // Imprime un mensaje indicando que el hilo se está procesando.

            try {
                Thread.sleep((int)(Math.random() * (600 - 100)) + 100); // Hace que el hilo duerma durante un tiempo aleatorio entre 100 y 600 milisegundos.
            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprime la traza de la pila si se produce una excepción InterruptedException.
            }
        }

        if (t != null) { // Si t no es null (es decir, si se creó un nuevo hilo).
            try {
                t.join(); // Espera a que el hilo t termine antes de continuar.
            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprime la traza de la pila si se produce una excepción InterruptedException.
            }
        }

        System.out.println(getName() + " finalizó."); // Imprime un mensaje indicando que el hilo ha terminado.
    }

    public static void main(String[] args) { // Método principal del programa.
        new Ej3("Hilo 5", 5).start(); // Crea e inicia un nuevo hilo llamado "Hilo 5" con n igual a 5.
    }
}
