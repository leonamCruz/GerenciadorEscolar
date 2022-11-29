package br.com.leonamCruz.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

public class UtilDataTest {
    int dia, mes, ano;
    @Before
    public void setup(){
        dia = Calendar.DAY_OF_MONTH;
        mes = Calendar.MONTH;
        ano = Calendar.YEAR;
    }
    @Test
    public void verificaSeDiaEhValido() {
        Assert.assertTrue(UtilData.verificaSeDiaEhValido(dia));
    }

    @Test
    public void verificaMesEhValido() {
        Assert.assertTrue(UtilData.verificaMesEhValido(mes));
    }

    @Test
    public void verificaAnoValido() {
        Assert.assertTrue(UtilData.verificaAnoValido(ano));
    }

    @Test
    public void verificaSeDiaEhValidoFalse(){
        Assert.assertFalse(UtilData.verificaSeDiaEhValido(dia + 1));
    }
    @Test
    public void verificaSeMesEhValidoFalse(){
        Assert.assertFalse(UtilData.verificaMesEhValido(mes + 1));
    }
    @Test
    public void verificaSeAnoEhValido(){
        Assert.assertFalse(UtilData.verificaAnoValido(ano + 1));
    }

}