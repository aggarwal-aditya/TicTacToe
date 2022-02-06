import java.util.Vector;

// Computer Class is inherited from the player class as many attributes are similar
final public class Computer extends Player {
	// Constructor for Computer Class
	Computer(String name, char letter, int number) {
		super(name, letter, number);
	}

	// This is the primary function in Computer Class which decides the next move of
	// computer
	void mark() {
		boolean moved = false;
		// Some helper variables to make the next move. Detailed explanation of the
		// logic is in README file
		int[] helper = new int[] { 6, 1, 8, 7, 5, 3, 2, 9, 4 };
		int[] utils = new int[] { 2, 7, 6, 9, 5, 1, 4, 3, 8 };
		char[][] arr = new char[3][3];
		// Using the method get grid in stateMachine class we import the present state
		// of grid
		arr = stateMachine.getGrid();
		Vector<Integer> positions = new Vector<>();
		Vector<Integer> self_positions = new Vector<>();
		// First the computer notes down the state of grid i.e. where Xs are present and
		// where Os are present
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] == 'X')
					positions.add((i * 3) + (j));
				else if (arr[i][j] == 'O')
					self_positions.add((i * 3) + (j));
			}
		}
		// First the computers check if its possible for it to win. It checks all pairs
		// of Xs and checks if another X at an empty location will make it win
		for (int i = 0; i < self_positions.size(); i++) {
			for (int j = 0; j < self_positions.size(); j++) {
				if (i != j && utils[self_positions.get(i)] + utils[self_positions.get(j)] < 15
						&& utils[self_positions.get(i)] + utils[self_positions.get(j)] > 5) {
					int diff = 15 - utils[self_positions.get(i)] - utils[self_positions.get(j)];
					if (!stateMachine.filled(helper[diff - 1])) {
						stateMachine.fill(helper[diff - 1], 'O');
						System.out.printf("\n3Computer has choosen to mark the square %d\n", helper[diff - 1]);
						moved = true;
						break;
					}
				}
			}
			if (moved)
				break;
		}
		// If the computer can't win, it will check if opponent will win in the next
		// move, it checks all pairs of Os and if it finds any O at empty position will
		// make opponent win, it blocks that position. If opponent plays very smart and
		// creates two such places, then the computer blocks one of them and looses.
		// More explanation in README.
		if (!moved) {
			for (int i = 0; i < positions.size(); i++) {
				for (int j = 0; j < positions.size(); j++) {
					if (i != j && utils[positions.get(i)] + utils[positions.get(j)] < 15
							&& utils[positions.get(i)] + utils[positions.get(j)] > 5) {
						int diff = 15 - utils[positions.get(i)] - utils[positions.get(j)];
						if (!stateMachine.filled(helper[diff - 1])) {
							stateMachine.fill(helper[diff - 1], 'O');
							System.out.printf("\nComputer has choosen to mark the square %d\n", helper[diff - 1]);
							moved = true;
							break;
						}
					}
				}
				if (moved)
					break;
			}
		}
		// If none of the above condition is satisfied the computer proceeds to mark the
		// squares adjacent to what it had marked earlier to win. But if it finds that
		// the center cell is unmarked, it marks that first as that gives it maximum
		// chances of winning.
		if (!moved) {
			if (!stateMachine.filled(5)) {
				stateMachine.fill(5, 'O');
				System.out.printf("\nComputer has choosen to mark the square %d\n", 5);
				moved = true;
			} else {
				for (int i = 1; i < 10; i++) {
					if (!stateMachine.filled(i)) {
						stateMachine.fill(i, 'O');
						System.out.printf("\nComputer has choosen to mark the square %d\n", i);
						moved = true;
						break;
					}
				}
			}
		}
	}
}
