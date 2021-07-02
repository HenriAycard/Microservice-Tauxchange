package fr.dauphine.miageif.tauxchange.TauxChange.Model;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TauxChangeTest {
    TauxChange tc;

    @Before
    public void setUp() {
        tc = new TauxChange();
    }

    @Test
    public void testGetSetID(){
        tc.setId(10011L);
        assertEquals(Long.valueOf(10011), tc.getId());
    }

    @Test
    public void testGetSetSource(){
        tc.setSource("USD");
        assertEquals("USD", tc.getSource());
    }

    @Test
    public void testGetSetDest(){
        tc.setDestination("GBP");
        assertEquals("GBP", tc.getDest());
    }

    @Test
    public void testGetSetTaux(){
        tc.setTaux(new BigDecimal(0.7175));
        assertEquals(new BigDecimal(0.7175), tc.getTaux());
    }

    @Test
    public void testGetSetDate(){
        tc.setDate("2021-06-21");
        assertEquals("2021-06-21", tc.getDate());
    }

    @Test
    public void testEquals(){
        tc.setId(10011L);
        tc.setSource("USD");
        tc.setDestination("GBP");
        tc.setTaux(new BigDecimal(0.7175));
        tc.setDate("2021-06-21");
        TauxChange tc2 = new TauxChange(10011L,"USD","GBP", new BigDecimal(0.7175), "2021-06-21");
        assertEquals(tc2,tc);
    }
}
