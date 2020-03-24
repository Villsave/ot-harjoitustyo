package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate unicafeExactum;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein(){         
        assertEquals("saldo: 0.10", kortti.toString());
    }
     
    @Test
    public void rahanLataaminenToimii(){
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
        
    }
    
    @Test
    public void rahaRiitti(){
        kortti.lataaRahaa(800);
        assertEquals(true, kortti.otaRahaa(400));
    }
    
    @Test
    public void rahaEiRiitä(){
        assertEquals(false, kortti.otaRahaa(400));
    }
    
    @Test
    public void saldoOikein(){
        assertEquals(10, kortti.saldo());
    }
}
