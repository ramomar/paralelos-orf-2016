package com.proypar;

import java.util.ArrayList;

public final class ResultadoAnalisis {
    public final Codon codonInicio;
    public final Codon codonParada;
    public final String secuenciaAnalizada;
    public final ArrayList<ArrayList<AminoAcido>> aminosEncontrados;
    public final int numeroDeMarco;

    ResultadoAnalisis(Codon _codonInicio, Codon _codonParada, String _secuenciaAnalizada,
                      ArrayList<ArrayList<AminoAcido>> _aminos, int _numeroDeMarco) {
        this.codonInicio = _codonInicio;
        this.codonParada = _codonParada;
        this.secuenciaAnalizada = _secuenciaAnalizada;
        this.aminosEncontrados = _aminos;
        this.numeroDeMarco =  _numeroDeMarco;
    }
}
