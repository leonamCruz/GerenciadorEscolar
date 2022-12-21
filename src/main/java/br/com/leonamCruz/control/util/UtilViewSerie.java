package br.com.leonamCruz.control.util;

public class UtilViewSerie {

    public static String alteraNumeroView(int serie){
        if(serie > 9){
            return String.valueOf(serie-9);
        } else return String.valueOf(serie);
    }
}
