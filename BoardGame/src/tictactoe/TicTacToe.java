package tictactoe;

import exception.InputInvalidException;
import exception.PlacementFailedException;
import game.AbstractGame;
import game.Coordinate;

public class TicTacToe extends AbstractGame {
	/** This Team array contains both teams involved in Tic-Tac-Toe */
	private final Team[] team;
	/** This integer keeps track of the current team's turn. */
	private int currentIndex;

	/**
	 * This constructor passes the Board of this game to the super class
	 * constructor and defines the instance variables of this object.
	 */
	public TicTacToe() {
		super(new TicTacToeBoard());

		team = new Team[2];
		team[0] = new Team(Team.TeamType.X);
		team[1] = new Team(Team.TeamType.O);

		currentIndex = 0;

	}

	/**
	 * This method represents a single placement of a piece by one of the
	 * players in the game. Then, the current team swaps.
	 */
	@Override
	public void next() {
		Coordinate input;

		try {
			input = getConsole().getSquareCoordinates(this);
		} catch (InputInvalidException e) {
			errorMessage(e.toString());
			return;
		}
		System.out.println();

		try {
			getBoard().attemptPlace(team[currentIndex].getPieceType(), input);
		} catch (PlacementFailedException e) {
			errorMessage(e.toString());
			return;
		}

		printBoard();
		currentIndex = currentIndex == 0 ? 1 : 0;

	}

	/**
	 * This method checks if the game should end. This method asks the board if
	 * the game should end and gives a message if the game has ended.
	 */
	@Override
	public void check() {
		char team = getBoard().gameEnd();

		if (team != ' ') {
			if (team == '_') {
				System.out.println("It's a tie!");
			} else {
				System.out.println(team + " won!");
			}

			endGame();
		}
	}
}