# Pacman
Sovellus on versio pacman-pelistä. Pelin tavoite on kerätä kaikki pisteet sokkelosta ja vältellä vihollisia.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Villsave/ot-harjoitustyo/blob/master/dokumentointi/Vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/Villsave/ot-harjoitustyo/blob/master/dokumentointi/Tuntikirjanpito.md)

[Käyttöohje](https://github.com/Villsave/ot-harjoitustyo/blob/master/dokumentointi/K%C3%A4ytt%C3%B6ohje.md)

[Arkkitehtuuri](https://github.com/Villsave/ot-harjoitustyo/blob/master/dokumentointi/Arkkitehtuuri.md)

## Komentorivitoiminnot

Projektin voi suorittaa komennolla
```
mvn compile exec:java -Dexec.mainClass=pacmanui.Main
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

Checkstyle-tarkastuksen saa tehtyä komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```

ja Javadocin komennolla
```
mvn javadoc:javadoc
```
