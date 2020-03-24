
package com.mycompany.unicafe;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate unicafeExactum;
    Maksukortti kortti;
    
    
    public KassapaateTest() {
    }
    
   
    @Before
    public void setUp() {
        unicafeExactum = new Kassapaate();
        kortti=new Maksukortti(0);

    }
    
    @Test
    public void luotuKassapaateRahatOikein() {
        assertEquals(100000, unicafeExactum.kassassaRahaa());
    }
    
    @Test
    public void luotuKassapaateEdullisetLounaatOikein() {
        assertEquals(0, unicafeExactum.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luotuKassapaateKalliitLounaatOikein() {
        assertEquals(0, unicafeExactum.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKateisosto(){
        unicafeExactum.syoEdullisesti(300);
        assertEquals(1, unicafeExactum.edullisiaLounaitaMyyty());
       
    }
    @Test
    public void edullinenKateisosto2(){
        unicafeExactum.syoEdullisesti(300);
        assertEquals(100240, unicafeExactum.kassassaRahaa());
       
    }
    
    @Test
    public void edullinenKateisosto3(){
        unicafeExactum.syoEdullisesti(100);
        assertEquals(100000, unicafeExactum.kassassaRahaa());
        assertEquals(0, unicafeExactum.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kallisKateisosto(){
        unicafeExactum.syoMaukkaasti(400);
        assertEquals(1, unicafeExactum.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kallisKateisosto2(){
        unicafeExactum.syoMaukkaasti(500);
        assertEquals(100400, unicafeExactum.kassassaRahaa());
       
    }
    
    @Test
    public void kallisKateisosto3(){
        unicafeExactum.syoMaukkaasti(100);
        assertEquals(100000, unicafeExactum.kassassaRahaa());
        assertEquals(0, unicafeExactum.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKorttiosto(){
        kortti.lataaRahaa(300);
        unicafeExactum.syoEdullisesti(kortti);
        assertEquals(1, unicafeExactum.edullisiaLounaitaMyyty());
       
    }
    
    @Test
    public void edullinenKorttiosto2(){
        kortti.lataaRahaa(300);
        assertEquals(true, unicafeExactum.syoEdullisesti(kortti));   
    }
    
    @Test
    public void edullinenKorttiosto3(){
        kortti.lataaRahaa(200);
        assertEquals(false, unicafeExactum.syoEdullisesti(kortti));   
    }
    
    @Test
    public void edullinenKorttiosto4(){
        kortti.lataaRahaa(200);
        assertEquals(0, unicafeExactum.edullisiaLounaitaMyyty());   
    }
    
    @Test
    public void edullinenKorttiosto5(){
        kortti.lataaRahaa(300);
        assertEquals(100000, unicafeExactum.kassassaRahaa());   
    }
    
    @Test
    public void kallisKorttiosto(){
        kortti.lataaRahaa(500);
        unicafeExactum.syoMaukkaasti(kortti);
        assertEquals(1, unicafeExactum.maukkaitaLounaitaMyyty());
       
    }
    
    @Test
    public void kallisKorttiosto2(){
        kortti.lataaRahaa(400);
        assertEquals(true, unicafeExactum.syoMaukkaasti(kortti));   
    }
    
    @Test
    public void kallisKorttiosto3(){
        kortti.lataaRahaa(200);
        assertEquals(false, unicafeExactum.syoMaukkaasti(kortti));   
    }
    
    @Test
    public void kallisKorttiosto4(){
        kortti.lataaRahaa(200);
        assertEquals(0, unicafeExactum.maukkaitaLounaitaMyyty());   
    }
    
    @Test
    public void kallisKorttiosto5(){
        kortti.lataaRahaa(400);
        assertEquals(100000, unicafeExactum.kassassaRahaa());   
    }
    
    @Test
    public void kortinlataus(){
        unicafeExactum.lataaRahaaKortille(kortti, 3000);
        assertEquals(3000, kortti.saldo());
    }
    
    @Test
    public void kortinlataus2(){
        unicafeExactum.lataaRahaaKortille(kortti, 3000);
        assertEquals(103000, unicafeExactum.kassassaRahaa()); 
    }
    
    @Test
    public void kortinlataus3(){
        unicafeExactum.lataaRahaaKortille(kortti, -3000);
        assertEquals(0, kortti.saldo());
    }
    @Test
    public void kortinlataus4(){
        unicafeExactum.lataaRahaaKortille(kortti, -3000);
        assertEquals(100000, unicafeExactum.kassassaRahaa()); 
    }
    
}