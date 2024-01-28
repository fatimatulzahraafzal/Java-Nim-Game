# Java-Nim-Game


Nim is a combinatorial game where two players alternately take turns in removing stones 
from several piles. The only rule is that each player must take at least one stone on their turn, 
but they may take more than one stone in a single turn as long as they all come from the same 
pile.

There are many variations of Nim. In this assignment, we will work on the one called Misère 
Nim, where the player who takes the last stone loses the game. In other words, the players' 
goal is to force the opponent to take the last stone.

References:

• https://brilliant.org/wiki/nim/
• https://en.wikisource.org/wiki/Nim,_A_Game_with_a_Complete_Mathematical_Theory

A winning strategy was proposed by an American mathematician Charles Leonard Bouton. In 
short, the player with a zero nim-sum of the pilesis in a losing position. Therefore, the winning 
strategy is to take the necessary quantity of stones from an appropriate pile to give your 
opponent a zero nim-sum. If you maintain this position throughout the game, you will win.


In the project, the task is to implement an AI player based on Bouton’s method with an object-oriented approach. The AI player should 
always perform the optimal play and win the game if the nim-sum of the piles is not zero in 
the player's turn. In the "code-file", the implementation of this code is provided. In the "code-test-file", unit tests for the AI player class using JUnit 4 are provided to ensure it works properly. "Gui.java" file is an attempt to create a Graphical User Interface to play the game, using Java Swing with several features like buttons, actions, text bars explored. Rest of the files are support files as the project falls under an object-oriented design.
