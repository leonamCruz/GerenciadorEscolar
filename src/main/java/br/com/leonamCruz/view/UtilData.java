package br.com.leonamCruz.view;

import java.time.Year;
import java.util.Calendar;

public class UtilData {
    public static boolean verificaSeDiaEhValido(int dia, int mes, int ano) throws ExceptionUtilData {
        if (dia > Calendar.getInstance().get(Calendar.DAY_OF_MONTH) && mes > Calendar.getInstance().get(Calendar.MONTH) && ano > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ExceptionUtilData("Ainda não chegamos nessa data");
        }
        var isBissexto = Year.isLeap(ano);

        if ((((mes == 2 && dia > 28) && !isBissexto) || (dia < 1)) || (isBissexto && dia > 29)) {
            throw new ExceptionUtilData("Reveja a data inserida");
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            if (dia > 30) {
                throw new ExceptionUtilData("Reveja a data inserida");
            }
        } else {
            if (dia > 31) {
                throw new ExceptionUtilData("Reveja a data inserida");
            }
        }
        return true;
    }

    public static boolean verificaMesEhValido(int mes) throws ExceptionUtilData {
        if (mes > 12 || mes < 1) {
            throw new ExceptionUtilData("Mês Inválido");
        }
        return true;
    }

    public static boolean verificaAnoValido(int ano) throws ExceptionUtilData {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        if (anoAtual - 120 >= ano) {
            throw new ExceptionUtilData("Acredito que você deveria estar morto... Ou então é o novo recordista mundial.");
        }
        if (ano > anoAtual) {
            throw new ExceptionUtilData("Acredito que você não tenha nascido no futuro...");
        }
        return true;
    }
}
