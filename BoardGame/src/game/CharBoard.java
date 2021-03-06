package game;

import exception.PlacementFailedException;

/**
 * This class creates a 2d array of squares and keeps track of the piece
 * locations, win conditions, and text display.
 */
public abstract class CharBoard {
	/**
	 * board a 2d array of characters that keep track of the pieces on the board
	 */
	private Square[][] board;

	/**
	 * This constructor takes the size of the board and Initializes the board.
	 * 
	 * @param size
	 */
	public CharBoard(int size) {
		board = new Square[size][size];

		initializeBoard();
	}

	/**
	 * This method sets the board the the position that it should initially be
	 * for the game that is running. The default version is turning every square
	 * into an empty square.
	 */
	public void initializeBoard() {
		for (int j = 0; j < board.length; j++)
			for (int i = 0; i < board[j].length; i++)
				board[j][i] = new Square(' ');
	}

	/**
	 * This method attempts to place the piece on the coordinate. If the
	 * placement fails, then it throws an exception.
	 * 
	 * @return if the piece was successfully placed or not
	 */
	public void attemptPlace(PieceType piece, Coordinate c) {
		if (canPlace(piece, c)) {
			place(piece, c);
		} else {
			throw new PlacementFailedException("The piece cannot be placed on square \"" + c.toAlphaNumericalString() + '\"');
		}
	}

	/**
	 * This method places a piece on the square the coordinates corresponds to
	 * without verification.
	 * 
	 * @param piece
	 *            the piece that will be placed
	 * @param c
	 *            the coordinates of the square the piece will be placed on
	 */
	public void place(PieceType piece, Coordinate c) {
		board[c.getCol()][c.getRow()].setChar(piece.getChar());
	}

	/**
	 * This method verifies that a piece can be placed on this square.
	 * 
	 * @param piece
	 *            the piece that will be checked
	 * @param c
	 *            the coordinates the piece will be checked on
	 * @return if the piece can be placed on the coordinate
	 */
	public abstract boolean canPlace(PieceType piece, Coordinate c);

	/**
	 * This method tells the user if the square has no piece on it.
	 * 
	 * @param c
	 *            the coordinate
	 * @return if the coordinate is empty
	 */
	public boolean isEmpty(Coordinate c) {
		return board[c.getCol()][c.getRow()].getChar() == ' ';
	}

	/**
	 * For every column, put a number. For every row, put a letter. Place the
	 * single letter representation of the piece inside the grid.
	 * 
	 * @return a text created board with all the square names and pieces on the
	 *         squares shown.
	 */
	public String toString() {
		String out = " ";
		out += labelEachColumn() + "\n";
		out += printAllRows() + "\n";

		return out;
	}
	
	protected String labelEachColumn() {
		String out = "";
		for (int i = 1; i <= board.length; i++)
			out += " " + labelColumn(i);
		
		return out;
	}
	
	protected String labelColumn(int i) {
		return "" + (i % 10 == 0 ? i / 10 : i % 10);
	}

	protected String printAllRows() {
		String out = "";
		for (int j = 0; j < board[0].length; j++) {
			out += printRowLabel(j) + " ";
			out += printRow(j) + "\n";
			out += printRowDivider(!isBottomRow(j)) + "\n";
		}
		return out;
	}

	protected String printRowLabel(int row) {
		return "" + (char)('A'+row);
	}
	
	protected String printRow(int j) {
		String out = "" + board[j][0].getChar();
		for (int i = 1; i < board.length; i++) {
			out += "|" + board[j][i].getChar();
		}
		return out;
	}
	
	protected String printRowDivider(boolean shouldPrint) {
		if(shouldPrint) {
			String out = "  -";
			for (int i = 1; i < board.length; i++)
				out += "--";
			return out;
		}
		else {
			return "";
		}
	}

	protected boolean isBottomRow(int row) {
		return board.length -1 == row;
	}
	
	/**
	 * This method detects if the game has ended and returns a notification a
	 * character that corresponds to the state of the game.
	 */
	public abstract char gameEnd();

	/**
	 * This method detects if the square at a coordinate exists.
	 * 
	 * @return if the square exists
	 */
	public boolean squareExists(Coordinate out) {
		return out.getCol() >= 0 && out.getCol() < board[0].length && out.getRow() >= 0 && out.getRow() < board.length;
	}

	/**
	 * This method returns the board that the game is placed on.
	 * 
	 * @return the board
	 */
	public char[][] getCharBoard() {
		char[][] out = new char[board.length][board[0].length];
		for (int j = 0; j < board.length; j++)
			for (int i = 0; i < board[j].length; i++)
				out[j][i] = board[j][i].getChar();
		return out;
	}
}