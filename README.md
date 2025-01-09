# Tic-tac-toe-scala
Tic-tac-toe game written in Scala

## How to run the game

### How to run the project
After clone the project, navigate to the folder tic-tac-toe-scala and run:
```console
sbt run
```


### How to run the tests
To run the tests execute the code below
```console
sbt test
```

![image](https://github.com/user-attachments/assets/aad132a7-2c79-4cfa-8c3e-1f5f2bfb7716)


Want to generate .jar?
```console
sbt package
```

Run the jar file using the code below: 
```console
java -jar 
```


-------------------------------------------
There is not a docker version for this project*

# About the project
What is Tic-tac-toe game?
Tic-Tac-Toe is a simple two-player game played on a 3x3 grid. 
The goal is to be the first player to align three of your symbols (either "X" or "O") in a horizontal, vertical, or diagonal row.

## How This project is organized? 
* Setup: The game starts with an empty 3x3 grid and will ask for the name of player 1 and player 2

* Players: There are two players: 
  * Player 1 uses the symbol "X".
  * Player 2 uses the symbol "O".


* Turns: Players take turns marking one empty cell in the grid with their symbol.
  * The player must follow the input pattern (row,column). For example: (1,2)


* Winning: A player wins if they create a line of three of their symbols in horizontal, vertical or diagonal line


* Draw: If all nine cells are filled and neither player has three in a row, the game ends in a draw.


# Complexity and Big (O) Notation
The project handles board operations with a complexity of O(n²) for checks and drawing, and O(1) for updates.






