package com.proypar;

public class AnalizadorTiemposEjecucion {
    public static void main(String[] args) {

        long t1, t2;

        int cantidadThreadsAProbar = 0;
        String nombreArchivo = "";
        try {
            nombreArchivo = args[0];
            cantidadThreadsAProbar = Integer.parseInt(args[1]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Uso: AnalizadorTiempos archivo.fa numero_de_threads_a_probar");
            System.exit(1);
        }

        t1 = System.currentTimeMillis();
        for (int i=0; i<cantidadThreadsAProbar; i++) {
            System.out.println("Corriendo con " + (i+1) + " thread(s)...");
            ORF.main(new String[] {nombreArchivo, Integer.toString(i+1)});
        }

        t2 = System.currentTimeMillis();

        System.out.println("\n\n CANTIDAD DE THREADS PROBADOS: " + cantidadThreadsAProbar);
        System.out.println(" TIEMPO TOTAL DE ANALISIS: " + (t2 - t1) + " ms");
    }
}
