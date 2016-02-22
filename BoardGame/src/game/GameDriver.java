package game;

import minesweeper.Minesweeper;
import othello.Othello;
import tictactoe.TicTacToe;

/**
 * This class runs the games that are in this game pack.
 */
public class GameDriver {

	public static void main(String[] args) {
		int selection;
		CustomScanner console = new CustomScanner();

		while (true) {
			System.out.println("1. TicTacToe\n2. Minesweeper\n3. Othello\n4. Help\n5. Exit");
			System.out.print("Please enter the number of the game you want " + "to play: ");

			selection = console.getInt(1, 5);

			switch (selection) {
			case 1:
				new TicTacToe().startGame();
				break;
			case 2:
				System.out.print("Enter a board size between 3 and 20: ");
				new Minesweeper(console.getInt(3, 20)).startGame();
				break;
			case 3:
				System.out.println("Not yet implemented");
				new Othello();
				break;
			case 4:
				displayHelp();
				break;
			case 5:
				System.exit(0);
			default:
				System.out.println("Try again");
				break;
			}

			console.pressToContinue();
		}
	}

	/**
	 * This method gives basic, general instructions about the key phrases the
	 * user can use and how the user can input square coordinates.
	 */
	public static void displayHelp() {
		System.out.println("\n Using the phrase \"Exit\" will completely close the program at any time.\n"
				+ " When a game is active, using the phrase \"Back\" or \"Menu\" will take you back to the main menu\n"
				+ " When indicating a square, the only accepted format is single letter then single number \"a1\".\n"
				+ " I hope you have fun.\n");
	}

}