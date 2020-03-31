# Pacman
Sovellus on versio pacman-pelistä. Pelin tavoite on kerätä kaikki pisteet sokkelosta ja vältellä vihollisia.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Villsave/ot-harjoitustyo/blob/master/dokumentointi/Otsovellus.md)

## Komentorivitoiminnot

Projektin voi suorittaa komennolla
```
mvn compile exec:java -Dexec.mainClass=pacman.Main
```
Testit suoritetaan komennolla

```
mvn test
```
Testikattavuusraportti voidaan luoda komennolla

```
mvn jacoco:report
```
Jar-tiedosto voidaan tehdä komennolla

```
mvn package
```
