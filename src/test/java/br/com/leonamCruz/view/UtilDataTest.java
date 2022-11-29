package br.com.leonamCruz.view;

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
}