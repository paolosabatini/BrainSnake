# BrainSnake
The project aims to implement the old-fashioned Snake game and train an AI using reinforcement learning to play at it. 

![](https://github.com/paolosabatini/BrainSnake/workflows/Java-CI/badge.svg)

## Documentation

A documentation of the code is auto-generated via `Doxygen` and `TravisCI`, available at this link: [https://paolosabatini.github.io/BrainSnake/](https://paolosabatini.github.io/BrainSnake/)

## Structure of the repository

The repository is structured in different directories, containing different parts of the project.
- *documentation*: it contains the LaTeX files for the documentation.
- *snake*: it contains the `java` code of the game


## Tips and tricks for running it

### Java code

First of all, the code has to be compiled first, no executable or built files are added to this repository. The code now rely on `json-simple` package from [here](https://code.google.com/archive/p/json-simple/). To compile the code you need to include the unzipped `jar` from the download. In case you move the `json-simple-1.1.jar` in the `snake/` directory:
```java
cd snake/
javac -cp json-simple-1.1.jar -d . *.java
```
Then, to run it:
```java
java -cp .:json-simple-1.1.jar ps.SnakeApp2D
```

## Authors and references

The code has been developed by Paolo Sabatini. Any use of the code for any purpose should contain the appropriate reference to this project.

The code is based on many tutorials and books (especially for the `java` part), listed here:
- Jan Bodnar, _Zetcode tutorial for Java 2D gaming_, http://zetcode.com/tutorials/javagamestutorial/ (2007 2010)
