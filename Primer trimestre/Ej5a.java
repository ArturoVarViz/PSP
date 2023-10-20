package com.example;

public class Ej5a {

    public static void main(String[] args) {
        // Creación del primer hilo
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Este es el código que se ejecutará cuando se inicie el hilo
                System.out.println("Hola, soy el hilo número 1");
            }
        });

        // Creación del segundo hilo
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                // Este es el código que se ejecutará cuando se inicie el hilo
                System.out.println("Hola, soy el hilo número 2");
            }
        });

        try {
            // Inicio del segundo hilo
            thread2.start();
            // Hacemos que el hilo principal espere a que termine el hilo 2 antes de continuar
            thread2.join();

            // Inicio del primer hilo
            thread1.start();
            // Hacemos que el hilo principal espere a que termine el hilo 1 antes de continuar
            thread1.join();
        } catch (InterruptedException e) {
            // Este bloque se ejecutará si cualquier llamada a join es interrumpida
            e.printStackTrace();
        }
    }
}
