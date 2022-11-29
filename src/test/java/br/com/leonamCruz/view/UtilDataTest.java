package br.com.leonamCruz.view;

import br.com.leonamCruz.util.UtilData;
import br.com.leonamCruz.util.excessao.ExceptionUtilData;
import org.junit.Assert;
import org.junit.Test;

public class UtilDataTest {

    @Test
    public void verificaSeDiaEhValido()  {
        try {
            Assert.assertTrue(UtilData.verificaSeDiaEhValido(22,9,2000));
        } catch (ExceptionUtilData exceptionUtilData){
            exceptionUtilData.printStackTrace();
            Assert.fail("Falha");
        }
    }

    @Test
    public void calculaIdade() {
        Assert.assertEquals(1,UtilData.calculaIdade(12,12,2020));
    }
}