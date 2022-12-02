package br.com.leonamCruz.control.util;

import br.com.leonamCruz.control.excessao.ExceptionUtilData;

import java.time.Year;
import java.util.Calendar;

public class UtilData {
    public static int calculaIdade(int dia, int mes, int ano) {
        int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
        int mesAtual = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int diaAtual = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int idade;

        idade = anoAtual - ano;

        if (mesAtual <= mes && diaAtual != dia) {
            idade--;
        }
        return idade;
    }

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

    public static String padraoBrasileiroDeData(String date) {
        // AAAA-MM-DD Internacional - Entra
        //DD-MM-AAAA Nacional - Sai
        char[] numerosSeparados = date.toCharArray();
        String dia, mes, ano;

        dia = String.valueOf(numerosSeparados[8]) + numerosSeparados[9];
        mes = String.valueOf(numerosSeparados[5]) + numerosSeparados[6];
        ano = String.valueOf(numerosSeparados[0]) + numerosSeparados[1] + numerosSeparados[2] + numerosSeparados[3];

        return dia + "-" + mes + "-" + ano;
    }

    public static int pegaDia(String dataBr) {
        String dia = String.valueOf(dataBr.charAt(0)) + dataBr.charAt(1);
        return Integer.parseInt(dia);
    }
    public static int pegaMes(String dataBr) {
        String mes = String.valueOf(dataBr.charAt(3)) + dataBr.charAt(4);
        return Integer.parseInt(mes);
    }
    public static int pegaAno(String dataBr) {
        String ano = String.valueOf(dataBr.charAt(6)) + dataBr.charAt(7) + dataBr.charAt(8) + dataBr.charAt(9);
        return Integer.parseInt(ano);
    }
}