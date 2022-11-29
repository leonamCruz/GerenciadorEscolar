package br.com.leonamCruz.view;

import java.util.Calendar;

public class UtilData {
    public static boolean verificaSeDiaEhValido(int dia){
        int diaDeHj = Calendar.DAY_OF_MONTH;
        return dia <= diaDeHj;
    }
    public static boolean verificaMesEhValido(int mes){
        int mesAtual = Calendar.MONTH;
        return mes <= mesAtual;
    }
    public static boolean verificaAnoValido(int ano){
        int anoAtual = Calendar.YEAR;
        return ano <= anoAtual;
    }
}
