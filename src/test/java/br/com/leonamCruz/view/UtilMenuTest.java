package br.com.leonamCruz.view;

import org.junit.Assert;
import org.junit.Test;
public class UtilMenuTest {
    @Test
    public void testVerificaOpcaoTrue() {
        Assert.assertTrue(new UtilMenu().verificaOpcao((short) 6, (short) 1, (short) 1));
        Assert.assertTrue(new UtilMenu().verificaOpcao((short) 6, (short) 1, (short) 6));

    }
    @Test
    public void testVerificaOpcaoFalse(){
        Assert.assertFalse(new UtilMenu().verificaOpcao((short) 6, (short) 1, (short) 10));
        Assert.assertFalse(new UtilMenu().verificaOpcao((short) 6, (short) 1, (short) -1));

    }
}