

//culpa de Marcos por decir lo de hacerlo en 3 clases
public class Ej4 {
   
    public static void main(String[] args) {
        // Creamos una instancia del hilo SumadorPares
        SumadorPares hiloPares = new SumadorPares();
        // Creamos una instancia del hilo SumadorImpares
        SumadorImpares hiloImpares = new SumadorImpares();
        // Creamos una instancia del hilo SumadorTerminaEnDosOTres
        SumadorTerminaEnDosOTres hiloTerminaEnDosOTres = new SumadorTerminaEnDosOTres();

        // Iniciamos el hilo SumadorPares
        hiloPares.start();
        // Iniciamos el hilo SumadorImpares
        hiloImpares.start();
        // Iniciamos el hilo SumadorTerminaEnDosOTres
        hiloTerminaEnDosOTres.start();
    }
}


class SumadorPares extends Thread {
    
    @Override
    public void run() {
        // Declaramos una variable suma e inicializamos en 0
        int suma = 0;
        // Iteramos desde 1 hasta 1000
        for (int i = 1; i <= 1000; i++) {
            // Si el número es par, lo sumamos a la variable suma
            if (i % 2 == 0) {
                suma += i;
            }
        }
        // Imprimimos la suma de los números pares
        System.out.println("Suma de números pares: " + suma);
    }
}


class SumadorImpares extends Thread {
    
    @Override
    public void run() {
        // Declaramos una variable suma e inicializamos en 0
        int suma = 0;
        // Iteramos desde 1 hasta 1000
        for (int i = 1; i <= 1000; i++) {
            // Si el número es impar, lo sumamos a la variable suma
            if (i % 2 != 0) {
                suma += i;
            }
        }
        // Imprimimos la suma de los números impares
        System.out.println("Suma de números impares: " + suma);
    }
}


class SumadorTerminaEnDosOTres extends Thread {
   
    @Override
    public void run() {
        // Declaramos una variable suma e inicializamos en 0
        int suma = 0;
        // Iteramos desde 1 hasta 1000
        for (int i = 1; i <= 1000; i++) {
            // Si el último dígito del número es 2 o 3, lo sumamos a la variable suma
            if (i % 10 == 2 || i % 10 == 3) {
                suma += i;
            }
        }
        // Imprimimos la suma de los números que terminan en dos o tres
        System.out.println("Suma de números que terminan en dos o tres: " + suma);
    }
}
