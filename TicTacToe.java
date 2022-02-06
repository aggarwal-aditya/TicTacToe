import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	public static void main(String[] args) {
		System.out.printf("Welcome to the Tic-Toe Game\n");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Please Select Game Mode\n Enter 1 for Player v/s Player mode \n Enter 2 for Player v/s Computer mode \n");
		int game_mode = input.nextInt();
		// We first ask the user to select game mode. Multiplayer ot single player.
		while (true) {
			if (game_mode == 1 || game_mode == 2)
				break;
			System.out.println(
					"You have made an invalid choice. Please Select again.\n Enter 1 for Player v/s Player mode \n Enter 2 for Player v/s Computer mode \n");
			game_mode = input.nextInt();
		}
		if (game_mode == 1) {
			// If we the player chooses 2 player mode we ask the players to enter their
			// name.
			int rematch = 1;
			String name = new String("");
			int count = 0;
			System.out.println("Please Enter Player 1 Name: ");
			while (name.length() == 0) {
				name = input.nextLine();
				count++;
				if (count == 2)
					break;
			}
			Player player1 = new Player(name, 'X', 1);
			name = "";
			System.out.println("Please Enter Player 2 Name: ");
			name = input.nextLine();
			Player player2 = new Player(name, 'O', 2);
			// We now start the game. This while loop is in hope that the user will want to
			// have a rematch after the game, we give the user an option to quit or rematch
			// after every game
			while (rematch == 1) {
				Random random = new Random();
				int temp = random.nextInt(2);// We do a toss to decide who will make the first move.
				// We generate a random number and based on its parity the toss outcome is
				// decided
				System.out.println("Computer is doing a toss to decide who will move first. Please Wait....");
				if (temp + 1 == 1) {
					System.out.printf("%s (Player %d) has won the toss and gets to move first\n\n", player1.name,
							temp + 1);
					stateMachine grid = new stateMachine();
					grid.display(); // We show the initial state of grid to the players
					boolean tie = true;
					long startTime = System.currentTimeMillis(); // We start a timer to time how many seconds did the
																	// game last
					int moves = 0;
					// As there are maximum 9 moves possible in TicTacToe we allow the game to go on
					// for 9 moves
					for (int i = 0; i < 9; i++) {
						moves++;
						// We alternately allot players turns using the parity of number of moves
						// happened
						if ((i & 1) == 0) {
							player1.mark(); // We invoke the mark method from player class
							grid.display(); // Display of grid after player 1 has marked
							// After each move we check if that player has won
							if (grid.winState(player1.letter())) {
								tie = false;
								System.out.printf("\nPlayer 1 has Won!\nWell played %s", player1.name);
								break;
							}
						} else {
							// Simmilat to player 1 movement
							player2.mark();
							grid.display();
							if (grid.winState(player2.letter())) {
								tie = false;
								System.out.printf("\nPlayer 2 has Won!\nWell played %s", player2.name);
								break;
							}
						}
					}
					long endTime = System.currentTimeMillis();
					// If the match is not won by anty player we declare it to be a tie.
					if (tie) {
						System.out.printf("\n The match is Tied. Well Played %s and %s ! \n", player1.name,
								player2.name);
					}
					System.out.println("\nSome Interesting Stats for the Game:");
					System.out.printf("The game lasted for %d seconds\n", (endTime - startTime) / 1000);
					System.out.printf("The game lasted for %d moves\n", moves);
					System.out.println("\nWant to have a Rematch?\nEnter 1 for rematch\nEnter 2 to exit");
					rematch = input.nextInt();
					// This is the same thing but in this the toss has been won by the other player
				} else {
					System.out.printf("%s (Player %d) has won the toss and gets to move first\n", player2.name,
							temp + 1);
					stateMachine grid = new stateMachine();
					grid.display();
					boolean tie = true;
					long startTime = System.currentTimeMillis();
					int moves = 0;
					for (int i = 0; i < 9; i++) {
						moves++;
						if ((i & 1) == 1) {
							player1.mark();
							grid.display();
							if (grid.winState(player1.letter())) {
								tie = false;
								System.out.printf("\nPlayer 1 has Won!\nWell played %s ", player1.name);
								break;
							}
						} else {
							player2.mark();
							grid.display();
							if (grid.winState(player2.letter())) {
								tie = false;
								System.out.printf("\nPlayer 2 has Won!\nWell played %s ", player2.name);
								break;
							}
						}
					}
					long endTime = System.currentTimeMillis();
					if (tie) {
						System.out.printf("\n The match is Tied. Well Played %s and %s! \n", player1.name,
								player2.name);
					}
					System.out.println("\nSome Interesting Stats for the Game:");
					System.out.printf("The game lasted for %d seconds\n", (endTime - startTime) / 1000);
					System.out.printf("The game lasted for %d moves\n", moves);
					System.out.println("\nWant to have a Rematch?\nEnter 1 for rematch\nEnter 2 to exit");
					rematch = input.nextInt();
				}
			}
		}
		// This is the beginning of player vs computer game mode
		else {
			int count = 0;
			// We ask the player for their name
			String name = new String("");
			System.out.println("Please Enter Your Name: ");
			while (name.length() == 0) {
				name = input.nextLine();
				count++;
				if (count == 2)
					break;
			}
			int rematch = 1;
			// Simmilar to player vs player mode we make two players but the player 2 is
			// instatiated using computer class constructor
			Player player1 = new Player(name, 'X', 1);
			Player player2 = new Computer("TicToe Grandmaster Computer", 'O', 1);
			while (rematch == 1) {
				// From here on the things are very simmilar. We do a toss and the gameplay
				// moves accordingly
				Random random = new Random();
				int temp = random.nextInt(2);// We do a toss to decide who goes first
				System.out.println(
						"Computer is doing a toss to decide who will move first. (Believe us we will do a fair toss) Please Wait....\n");
				if (temp + 1 == 1) {
					System.out.printf("You have won the toss and get to move first\n");
					stateMachine grid = new stateMachine();
					grid.display();
					boolean tie = true;
					long startTime = System.currentTimeMillis();
					int moves = 0;
					for (int i = 0; i < 9; i++) {
						moves++;
						if ((i & 1) == 0) {
							player1.mark();
							grid.display();
							if (grid.winState(player1.letter())) {
								tie = false;
								System.out.printf("\nYou have Won!\nWell played");
								break;
							}
						} else {
							player2.mark();
							grid.display();
							if (grid.winState(player2.letter())) {
								tie = false;
								System.out.printf(
										"\nYou have lost unfortunately. But dont worry the Computer has been at practise for years!\n ");
								break;
							}
						}
					}
					long endTime = System.currentTimeMillis();
					if (tie) {
						System.out.printf("\n The match is Tied. Well Played %s! \n", player1.name);
					}
					System.out.println("\nSome Interesting Stats for the Game:");
					System.out.printf("The game lasted for %d seconds\n", (endTime - startTime) / 1000);
					System.out.printf("The game lasted for %d moves\n", moves);
					System.out.println("\nWant to have a Rematch?\nEnter 1 for rematch\nEnter 2 to exit");
					rematch = input.nextInt();
				} else {
					System.out.printf(" TicToe Grandmaster Computer has won the toss and gets to move first\n");
					stateMachine grid = new stateMachine();
					grid.display();
					boolean tie = true;
					long startTime = System.currentTimeMillis();
					int moves = 0;
					for (int i = 0; i < 9; i++) {
						moves++;
						if ((i & 1) == 1) {
							player1.mark();
							grid.display();
							if (grid.winState(player1.letter())) {
								tie = false;
								System.out.printf("\nYou have Won Won!\nWell played");
								break;
							}
						} else {
							player2.mark();
							grid.display();
							if (grid.winState(player2.letter())) {
								tie = false;
								System.out.printf(
										"\nYou have lost unfortunately. But dont worry the Computer has been at practise for years!");
								break;
							}
						}
					}
					long endTime = System.currentTimeMillis();
					if (tie) {
						System.out.printf("\n The match is Tied. Well Played %s \n", player1.name);
					}
					System.out.println("\nSome Interesting Stats for the Game:"); // We print some instersting stats
																					// from the game
					System.out.printf("The game lasted for %d seconds\n", (endTime - startTime) / 1000);
					System.out.printf("The game lasted for %d moves\n", moves);
					System.out.println("\nWant to have a Rematch?\nEnter 1 for rematch\nEnter 2 to exit");
					rematch = input.nextInt();
				}
			}
		}
	}
}
