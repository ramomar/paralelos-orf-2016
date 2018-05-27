package com.proypar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ORF {

    public static void main(String[] args) {
        File archivo = null;
        int THREADS = 0;
        long t1, t2;
        try {
            archivo = new File(args[0]);
            THREADS = Integer.parseInt(args[1]);
            if (!archivo.exists()) throw new FileNotFoundException("el archivo con el nombre " +
                    args[0] + " no existe");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Uso ORF nombre_archivo.fa num_threads");
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }

        t1 = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(THREADS);

        List<String> secuenciasAAnalizar = LectorFASTA.leerSecuenciasDeArchivo(archivo);

        List<EncontrarAminosRun> analizadores = new ArrayList<>();
        List<Future> futurosAnalizador = new ArrayList<>();

        for (int i = 0; i < secuenciasAAnalizar.size(); i++) {
            EncontrarAminosRun a1 = new EncontrarAminosRun(secuenciasAAnalizar.get(i), 1);
            EncontrarAminosRun a2 = new EncontrarAminosRun(secuenciasAAnalizar.get(i), 2);
            EncontrarAminosRun a3 = new EncontrarAminosRun(secuenciasAAnalizar.get(i), 3);

            analizadores.add(a1);
            analizadores.add(a2);
            analizadores.add(a3);

            futurosAnalizador.add(pool.submit(a1));
            futurosAnalizador.add(pool.submit(a2));
            futurosAnalizador.add(pool.submit(a3));
        }

        System.out.println("analizando secuencias...");

        for (Future<Future> f : futurosAnalizador) {
            try {
                f.get();
            } catch (Exception e) {
                System.out.println("error al analizar secuencias.");
                System.exit(1);
            }
        }

        System.out.println("obteniendo resultados...");
        for (EncontrarAminosRun a : analizadores) {
            System.out.println(a.obtenerAnalisis().aminosEncontrados);
        }


        t2 = System.currentTimeMillis();

        System.out.println("Secuencias analizadas: " + secuenciasAAnalizar.size());
        System.out.println("Cantidad de threads utilizados: " + THREADS);
        System.out.println("Tiempo total: " + (t2 - t1) + " ms");
        guardarResultadoEjecucionEnArchivo(THREADS, (t2-t1), secuenciasAAnalizar.size());
        System.exit(0);
    }

    private static void guardarResultadoEjecucionEnArchivo(int cantidadThreads, long tiempoTotal,
                                                           int numSecuenciasAnalizadas) {
        PrintWriter wtr = null;
        try {
            wtr = new PrintWriter(new BufferedWriter(new FileWriter("resultados.txt", true)));
            wtr.println(cantidadThreads + " " + tiempoTotal + " " + numSecuenciasAnalizadas);
            wtr.close();
        } catch (IOException e) {
            System.out.println("occurio un error al intentar escribir el resultado");
            System.exit(1);
        }
    }
}
