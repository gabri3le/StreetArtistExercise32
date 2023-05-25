package StreetArtist;

import java.util.concurrent.Semaphore;
import java.util.Random;

public class StreetArtist {
    private static final int NumeroSedie = 4;
    private static final int TempoMassimoDiAttesa = 10000; // maximum wait time in milliseconds
    private static final Semaphore SedieDisponibili = new Semaphore(NumeroSedie, true);
    private static final Random random = new Random();
    private static int NumeroCliente = 0;
    private static final boolean[] chairs = new boolean[NumeroSedie];

    public static void main(String[] args) {
        while (true) {
            new Thread(new Customer()).start();
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Customer implements Runnable {
        private int N;

        public Customer() {
            synchronized (StreetArtist.class) {
                N = ++NumeroCliente;
            }
        }

        @Override
        public void run() {
            try {
                if (!SedieDisponibili.tryAcquire(TempoMassimoDiAttesa, java.util.concurrent.TimeUnit.MILLISECONDS)) {
                    System.out.println("Cliente " + N + " ha rinunciato ad aspettare ad una sedia libera.");
                    return;
                }
                int chairNumber = -1;
                synchronized (chairs) {
                    for (int i = 0; i < NumeroSedie; i++) {
                        if (!chairs[i]) {
                            chairs[i] = true;
                            chairNumber = i;
                            break;
                        }
                    }
                }
                System.out.println("Il cliente " + N + " si è seduto sulla sedia " + (chairNumber + 1) + " e aspetta il suo turno.");
                Thread.sleep(random.nextInt(10000));
                System.out.println("Il cliente " + N + " sta facendo disegnare il suo ritratto.");
                Thread.sleep(random.nextInt(10000));
                System.out.println("Il ritratto del cliente " + N + " è finito.");
                synchronized (chairs) {
                    chairs[chairNumber] = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            	SedieDisponibili.release();
            }
        }
    }
}