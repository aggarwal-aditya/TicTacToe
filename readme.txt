TicTacToe.java contains the main function. To run the programme compile all files, including the TicTacToe.java. Following this run TicTacToe file.

Generic Information
1) First, the computer asks you to select game mode single-player or two-player. 
2) Then, we need to enter player(s) name(s). If we want to play in anonymous mode, we can skip this process by pressing enter.
3) Player 1 is always assigned the symbol cross (X), and Player 2 (or computer) is given the symbol circle O
4) Since moving first is a significant advantage in TicTacToe; hence we always toss for it. We generate a random number (0/1), and based on its parity; we decide who gets to move first.
5) The game then begins, and after each move, the player's name (along with player number & their symbol) is printed, asking them to choose the cell they want to mark.
6) The square is a 3*3 grid with the cells labelled from 1 to 9. At Each move, the user enters a number to place their symbol. For help and easiness, after each move, the state of the grid is printed with X and O marked. The non-marked cells are represented by their numbers from 1-9.
7) Once the game gets over, some stats like total moves total time are displayed. Then the user is prompted for a rematch, or they may exit.

Code Specific Information
a) How computer (I have named it TicTacToe Grandmaster Computer) decides it moves
-First, it iterates through the complete board and stores the location of all X and all O.
-Then it decides if there is any square which, if marked by computer, will result in its win. If so it marks that square and wins. If there are multiple such squares, the computer marks one if them and wins.
-If not then the computer checks if there is any square which if marked by opponent will result in their win. If so it marks that square. If there are multiple such squares, computer unfortunately can't do anything and marks one of them to lose.
-If none of the above condition is satisfied computer first checks if the middle square is empty (as that gives maximum combinations for winning). If its empty it marks that. If not then it marks a square adjacent to the previous marked. If thats already filled, then it marks the first empty square it encounters starting from 1.
-This way the computer plays very efficiently

b) How do we decide if there is a winner at the present state of board or how does the computer know it can or opponent can win in the next move?
-We use a magic square approach to this. Now what is magic square
		|	2	|	7	|	6	|
		|	9	|	5	|	1	|
		|	4	|	3	|	8	|
If you observe the sum of every row, the column or diagonal is 15. (There are multiple such possibilities but we use this without any particular bias).
-Now, we exploit this fact and to check if there is winer at the present movement we take sum of all boxes where an X or O is placed in pair of three. If any such sum results in 15, we have a winner.
-To understand this in a better way, consider X are placed at 2,5,8,4. Now we take sum of 3 at a time (i.e 4C3 combinations) and check if any combination gives 15. Here 2+5+8=15 and hence we declare X as the winner.
-Note that in a TicTacToe game, a player can maximally place 5 symbols of one type. So we need only 5*5*5=125 sum checks at max to determine a winner (in most cases, this will be much less), which is very efficient. And can be done very quickly.
-Now the computer also decides in the same way if there is any chance to win, it computes sum in pairs of two and subtracts it from 15. It checks if the square with the corresponding number in the magic square is empty in the TicTacToe grid, it marks it to win. We do some checking here to ensure combinations from the same row or column or diagonal are only checked.
-The computer also similarly checks the opponent win condition, the difference is very trivial; it just checks the opponent's symbol locations instead of its own.

Note: Please see that magic square is not related to the user interface; it's just used in code to check winner status make computer moves. The 3*3 TicTacToe grid is entirely different from this.

c) How do we mark the cell with the required symbol
- We prompt the user to enter the square number where he wants to place their symbol. Following this, we check if the number entered is valid (the square should exist and should not have been marked earlier), and then fill the 2D array of grid with the required symbol at correct position.
- Checking if the grid is filled or not at a particular position is very trivial and is simple check

d) How do we decide to draw?
-We allow in total 9 moves to be played, if there is no winner even after that, it obviously means the match is drawn.

e) The computer class is inherited from the player class as some functions were identical.
f) The variable letter (i.e. X or O) is kept private to ensure security (Encapsulation)

