package com.proypar;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public final class AnalizadorSecuencias {

    public static ResultadoAnalisis analizarSecuencia(String _secuencia, int _marco) {
        List<AminoAcido> aminoAcidos = new ArrayList<>();

        String secuencia = _secuencia;

        switch (_marco) {
            case 1: break;
            case 2: secuencia = secuencia.substring(1); break;
            case 3: secuencia = secuencia.substring(2); break;
            default: throw new IllegalArgumentException("marco erroneo");
        }

        int lSec = secuencia.length();
        Boolean leyendoMarco = false;

        Codon codonInicio = null;
        Codon codonParada = null;

        ResultadoAnalisis res = null;

        ArrayList<ArrayList<AminoAcido>> secAminoacidos = new ArrayList<>();

        int numSecs = 0;

        for (int i=0; (lSec - i - 2) > 0 ; i+=3) {
            String marcoLectura =
                    Character.toString(secuencia.charAt(i)) +
                    Character.toString(secuencia.charAt(i+1)) +
                    Character.toString(secuencia.charAt(i+2));

            Codon codon = Codon.cadenaACodon(marcoLectura);

            if (Codon.esCodonDeInicio(codon)) {
                codonInicio = codon;
                leyendoMarco = true;
                secAminoacidos.add(new ArrayList<>());
                numSecs+=1;
            }

            if (leyendoMarco) {
                secAminoacidos.get(numSecs-1).add(codon.aminoAcido);
            }

            if (Codon.esCodonDeParada(codon) && leyendoMarco) {
                codonParada = codon;
                leyendoMarco = false;
            }

        }

        return new ResultadoAnalisis(codonInicio, codonParada, _secuencia, secAminoacidos, _marco);
    }

    public static void main(String args[]) {
        File archivo = new File(args[0]);
        List<String> secuencias = LectorFASTA.leerSecuenciasDeArchivo(archivo);

        for (String sec : secuencias) {
            ResultadoAnalisis analisis = analizarSecuencia(sec, 1);
            for (ArrayList<AminoAcido> am : analisis.aminosEncontrados) System.out.println(am);
        }
    }
}
