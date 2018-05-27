package com.proypar;

public enum Codon {

    // Referencia tomada de https://en.wikipedia.org/wiki/Genetic_code#RNA_codon_table

    TTT("ttt", AminoAcido.F),
    TTC("ttc", AminoAcido.F),
    TTA("tta", AminoAcido.L),
    TTG("ttg", AminoAcido.L),

    CTT("ctt", AminoAcido.L),
    CTC("ctc", AminoAcido.L),
    CTA("cta", AminoAcido.L),
    CTG("ctg", AminoAcido.L),

    ATT("att", AminoAcido.I),
    ATC("atc", AminoAcido.I),
    ATA("ata", AminoAcido.I),
    ATG("atg", AminoAcido.M, TipoCodon.INICIO),

    GTT("gtt", AminoAcido.V),
    GTC("gtc", AminoAcido.V),
    GTA("gta", AminoAcido.V),
    GTG("gtg", AminoAcido.V),

    TCT("tct", AminoAcido.S),
    TCC("tcc", AminoAcido.S),
    TCA("tca", AminoAcido.S),
    TCG("tcg", AminoAcido.S),

    CCT("cct", AminoAcido.P),
    CCC("ccc", AminoAcido.P),
    CCA("cca", AminoAcido.P),
    CCG("ccg", AminoAcido.P),

    ACT("act", AminoAcido.T),
    ACC("acc", AminoAcido.T),
    ACA("aca", AminoAcido.T),
    ACG("acg", AminoAcido.T),

    GCT("gct", AminoAcido.A),
    GCC("gcc", AminoAcido.A),
    GCA("gca", AminoAcido.A),
    GCG("gcg", AminoAcido.A),

    TAT("tat", AminoAcido.Y),
    TAC("tac", AminoAcido.Y),
    TAA("taa", AminoAcido.PAR, TipoCodon.PARADA),
    TAG("tag", AminoAcido.PAR, TipoCodon.PARADA),

    CAT("cat", AminoAcido.H),
    CAC("cac", AminoAcido.H),
    CAA("caa", AminoAcido.Q),
    CAG("cag", AminoAcido.Q),

    AAT("aat", AminoAcido.N),
    AAC("aac", AminoAcido.N),
    AAA("aaa", AminoAcido.K),
    AAG("aag", AminoAcido.K),

    GAT("gat", AminoAcido.D),
    GAC("gac", AminoAcido.D),
    GAA("gaa", AminoAcido.E),
    GAG("gag", AminoAcido.E),

    TGT("tgt", AminoAcido.C),
    TGC("tgc", AminoAcido.C),
    TGA("tga", AminoAcido.PAR, TipoCodon.PARADA),
    TGG("tgg", AminoAcido.W),

    CGT("cgt", AminoAcido.R),
    CGC("cgc", AminoAcido.R),
    CGA("cga", AminoAcido.R),
    CGG("cgg", AminoAcido.R),

    AGT("agt", AminoAcido.S),
    AGC("agc", AminoAcido.S),
    AGA("aga", AminoAcido.R),
    AGG("agg", AminoAcido.R),

    GGT("ggt", AminoAcido.G),
    GGC("ggc", AminoAcido.G),
    GGA("gga", AminoAcido.G),
    GGG("ggg", AminoAcido.G),

    NNN("nnn", AminoAcido.PAR); // Codon nulo

    public final String nucleotidos;
    public final AminoAcido aminoAcido;
    public final TipoCodon significado;

    Codon(String _nucleotidos, AminoAcido _aminoAcido, TipoCodon _significado) {
        this.nucleotidos = _nucleotidos;
        this.aminoAcido = _aminoAcido;
        this.significado = _significado;
    }

    Codon(String _nucleotidos, AminoAcido _aminoAcido) {
        this.nucleotidos = _nucleotidos;
        this.aminoAcido = _aminoAcido;
        this.significado = TipoCodon.CODIFICANTE;
    }

    private static Boolean esNucletidoInvalido(char ch) {
        char c = Character.toLowerCase(ch);
        return c!='a' && c!='t' && c!='g' && c!='c' && c!='n';
    }

    private static Boolean cadenaPuedeSerCodon(String str) {
        return str.length() == 3;
    }

    private static Boolean todosLosNucleotidosSonValidos(String str) {
        for (char c : str.toCharArray()) if (esNucletidoInvalido(c)) return false;
        return true;
    }

    private static Codon encontrarCodonPorCadena(String str) {
        for (Codon c : Codon.values())
            if (c.nucleotidos.equalsIgnoreCase(str.trim())) return c;

        return Codon.NNN;
    }

    public static Codon cadenaACodon(String str) {
        if (!cadenaPuedeSerCodon(str))
            throw new IllegalArgumentException("numero invalido de nucleotidos (deben ser 3)");

        if (!todosLosNucleotidosSonValidos(str))
            throw new IllegalArgumentException("nucleotido invalido");

        return encontrarCodonPorCadena(str);
    }

    public static Boolean esCodonDeInicio(Codon cod) {
        return cod.significado == TipoCodon.INICIO;
    }

    public static Boolean esCodonDeParada(Codon cod) {
        return cod.significado == TipoCodon.PARADA;
    }
}
