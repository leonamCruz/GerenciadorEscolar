package br.com.leonamCruz.util;

import br.com.leonamCruz.control.util.UtilData;
import org.junit.Assert;
import org.junit.Test;


public class UtilDataTest {

    @Test
    public void padraoBrasileiroDeData() {
        Assert.assertEquals("22-09-2000", UtilData.padraoBrasileiroDeData("2000-09-22"));
    }
    @Test
    public void pegaDia(){
        Assert.assertEquals(22,UtilData.pegaDia("22-09-2000"));
    }
}