import java.util.Vector;

//This class stores the current state of grid
public class stateMachine {
	static char[][] arr = new char[3][3];

//Constructor for stateMachine class. Initially all the cells are marked from 1 to 9
	stateMachine() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int num = (i * 3) + (j + 1) + 48;
				arr[i][j] = (char) num;
			}
		}
	}

	// method to get the current status of the grid.
	static char[][] getGrid() {
		return arr;
	}

	// Method to check if a particular cell is filled or not
	static boolean filled(int x) {
		if (arr[(x - 1) / 3][(x - 1) % 3] == 'X')
			return true;
		if (arr[(x - 1) / 3][(x - 1) % 3] == 'O')
			return true;
		return false;
	}

	// Method to mark a particular cell in the grid
	static void fill(int x, char letter) {
		arr[(x - 1) / 3][(x - 1) % 3] = letter;
	}

	// Method to print the current state of grid
	void display() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("\t\t %s", arr[i][j]);
			}
			System.out.print("\n\n");
		}
	}

	// Method to check if the current state of board has a winner. For detailed
	// understanding of logic reffer readme file
	boolean winState(char letter) {
		int[] helper = new int[] { 4, 9, 2, 3, 5, 7, 8, 1, 6 };
		Vector<Integer> positions = new Vector<>();
		// We first store all the positions of X or O for which we want to check the win
		// state
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (arr[i][j] == letter)
					positions.add((i * 3) + (j));
			}
		}
		// We check if sum of positions of any three X or O in corresponding magic sqaure
		// is 15. If so we have a winner.
		for (int i = 0; i < positions.size(); i++) {
			for (int j = 0; j < positions.size(); j++) {
				for (int k = 0; k < positions.size(); k++) {
					if (i != j && i != k && j != k) {
						if (helper[positions.get(i)] + helper[positions.get(j)] + helper[positions.get(k)] == 15) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
