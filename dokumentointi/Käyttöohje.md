# Käyttöohje
## Konfigurointi
Ohjelma olettaa, että juurikansiosta löytyvät sen tarvitsemat kuvatiedostot.

```
food.png
pacman_1.png
pacman_2.png
pacman_3.png
pacman_4.png
ghost_0.png
ghost_1.png
ghost_2.png
ghost_3.png
brick.png
```

## Käynnistys
Ohjelman voi käynnistää komennolla
```
java -jar Pacman.jar
```
Sovellus avautuu valikkoon, jossa voi tarkastella parhaita pisteitä, pelata antamalla nimimerkin tai sulkea sovelluksen.
<img src="https://github.com/Villsave/ot-harjoitustyo/blob/master/dokumentointi/Img/startmenu.png">

## Peli
Pelin vasemmassa yläkulmassa näkyvät pisteet sekä jäljellä olevat elämät. Pelaaja saa pisteitä syödessään ruokaa, mutta menettää pisteitä ja elämän osuessaan haamuun. Hahmoa ohjataan nuolinäppäimillä. Mitä enemmän hahmo on syönyt, sen nopeammin haamut liikkuvat.
<img src="https://github.com/Villsave/ot-harjoitustyo/blob/master/dokumentointi/Img/game.png">
Kun joko kaikki ruoka on syöty tai pelaaja on kuollut kolmesti, peli palaa takaisin aloitusruutuun.
