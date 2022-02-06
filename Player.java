import java.util.Scanner;

public class Player {
	String name;
	private char letter;
	int number;

	// Constructor for Player class
	Player(String name, char letter, int number) {
		this.name = name;
		this.letter = letter;
		this.number = number;
	}

	// Defining a method to get the symbol of player as it is a private variable
	char letter() {
		return letter;
	}

	// This is a primary function in the player class. It asks the user to mark a
	// sqaure and does some valiation for that square. If square can be marked it
	// does it.
	void mark() {
		System.out.printf("%s (Player %d) Please select the square where you want to place %s: ", name, number, letter);
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int user_input = input.nextInt(); // We scan the user input for the square he wants to mark
		boolean checker = false;
		// We check if the given square exists and is empty, if so we proceed else we
		// again ask the user to enter a correct number.
		while (!checker) {
			if (user_input > 9 || user_input < 1) {
				System.out.println("Please choose a valid square, The square choosen does not exist ");
				user_input = input.nextInt();
			} else if (stateMachine.filled(user_input)) {
				System.out.println("The choosen square is already marked. Please Choose another Square ");
				user_input = input.nextInt();
			} else {
				stateMachine.fill(user_input, letter);
				checker = true;
			}
		}
	}
}
