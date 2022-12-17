# Conways Game of Life


![Java CI with Maven](https://github.com/Computer-Kurzweil/conwaysgameoflife/workflows/Java%20CI%20with%20Maven/badge.svg)

**The famous Cellular Automata by John Horton Conway**

## Abstract

Green food appears in a simulatedEvolutionModel with red moving cells. These cells eat the food if it is on their position.
Movement of the cells depends on random and their DNA. A fit cell moves around and eats enough to reproduce.
Reproduction is done by splitting the cell and randomly changing the DNA of the two new Cells.
If a cell doesn't eat enough, it will first stand still and after a while it dies.


## Run the Desktop Application

```
git clone https://github.com/Computer-Kurzweil/conwaysgameoflife.git
cd conwaysgameoflife
./mvnw
```

## Screenshot

![Early Screen](src/main/resources/img/screen1.png)

![Later Screen](src/main/resources/img/screen2.png)


## UML Class Model
![UML Class Model](src/main/resources/img/Class_Model.jpg)


## Repositories
* [Github Repository](https://github.com/Computer-Kurzweil/conwaysgameoflife)
* [Maven Project Reports](https://java.woehlke.org/conwaysgameoflife)
