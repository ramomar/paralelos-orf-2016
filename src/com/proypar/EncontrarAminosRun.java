package com.proypar;

import java.lang.Runnable;

public class EncontrarAminosRun implements Runnable {

    private String secuencia;
    private int numeroMarco;
    private ResultadoAnalisis analisis;
    private Boolean yaSeCorrio = false;

    EncontrarAminosRun(String _secuencia, int _numeroMarco) {
        this.secuencia = _secuencia;
        this.numeroMarco = _numeroMarco;
    }

    public void run() {
        analisis = AnalizadorSecuencias.analizarSecuencia(this.secuencia, this.numeroMarco);
        yaSeCorrio = true;
    }

    public ResultadoAnalisis obtenerAnalisis() {
        return this.analisis;
    }
}
