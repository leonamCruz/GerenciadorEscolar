package br.com.leonamCruz.view;

public class UtilMenu {

    public boolean verificaOpcao(short opcaoMaior, short opcaoMenor, short entradaUser){
        return entradaUser <= opcaoMaior && entradaUser >= opcaoMenor;
    }

}
