package com.proypar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LectorFASTA {
    public static List<String> leerSecuenciasDeArchivo(File archivo) {
        List<String> secuencias = new ArrayList<>();
        FileReader reader;
        BufferedReader bufferedReader;
        try {
            reader = new FileReader(archivo);
            bufferedReader = new BufferedReader(reader);
            String linea, secuencia = "";
            int numSecuencias = 0;
            while ((linea = bufferedReader.readLine()) != null) {
                if (esInicioDeSecuencia(linea)) {
                    numSecuencias++;
                    secuencias.add(numSecuencias-1, "");
                } else {
                    secuencias.set(numSecuencias-1, secuencias.get(numSecuencias-1) + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("el archivo no existe");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("ocurrio un problema al intentar leer el archivo");
            System.exit(1);
        }
        return secuencias;
    }

    private static Boolean esInicioDeSecuencia(String linea) {
        return linea.startsWith(">");
    }
    
    public static void main(String args[]) {
        File archivo = new File(args[0]);
        List<String> secuencias = LectorFASTA.leerSecuenciasDeArchivo(archivo);
        for (String s : secuencias) System.out.println(s + "\n");
    }
}
