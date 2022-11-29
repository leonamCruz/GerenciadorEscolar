package br.com.leonamCruz.view;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class UtilMenu {
       public String saudacoes(@NotNull Date date){
        int horas = date.getHours();
        if(horas >= 0 && horas < 6){
            return "Boa Madrugada... Mas, você deveria está dormindo.";
        } else if (horas >= 6 && horas < 12) {
            return "Bom Dia";
        } else if (horas >=12 && horas < 18) {
            return "Boa Tarde";
        } else if (horas >=18 && horas <=23 ) {
            return "Boa Noite, bora dormir?";
        } else return "Não consegui acessar seu relógio...";
    }
}
