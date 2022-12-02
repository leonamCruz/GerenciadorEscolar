package br.com.leonamCruz.control.util;

import org.jetbrains.annotations.NotNull;

public class UtilNome {

    public static boolean maiorQueSetenta(int quantidadeDeCaracteres){
        return quantidadeDeCaracteres > 68;
    }
    public static String normalizaNome(@NotNull String nome){
        char[] letrinhas = nome.trim().toLowerCase().toCharArray();
        for(int posicao = 0; posicao < letrinhas.length ; posicao ++){
            if(Character.isWhitespace(letrinhas[posicao])){
                letrinhas[posicao + 1] = Character.toUpperCase(letrinhas[posicao + 1]);
            }
        }
        letrinhas[0] = Character.toUpperCase(letrinhas[0]);
        return new String(letrinhas);
    }

    public static boolean temCaracterInvalido (@NotNull String nome){
        if(temSoEspaco(nome)){
            return true;
        }
        String nomeVerificacao = normalizaNome(nome);
        char[] osQNaoPode = {'’', '!', '@', '#', '$', '%', '¨', '&',
                '*', '(', ')', '-', '_', '+', '§', '=', '/', '°', '?', ';', ':', '.', '>', ',', '<'
                , '|', '[', '{', ']', '}', 'º', 'ª', '¹', '²', '³', '£', '¢', '¬', '\'', '\\'};

        for (int i = 0; i < nomeVerificacao.length(); i++) {
            char chr = nomeVerificacao.charAt(i);
            for (char c : osQNaoPode) {
                if (c == chr) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean temSoEspaco(String nome){
        char[] letrinhas = nome.toCharArray();
        var qntCaracter = letrinhas.length;
        var qntEspacos = 0;
        for (char letrinha : letrinhas) {
            if (Character.isWhitespace(letrinha)) {
                qntEspacos++;
            }
        }
        return qntEspacos == qntCaracter;
    }
}
