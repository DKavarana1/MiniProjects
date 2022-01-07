import java.util.ArrayList;

public class TicTacToeGame {
	ArrayList <Square> squares = new ArrayList <Square>();
	static final int BOARD_SIZE = 9;
	
	public TicTacToeGame() {
		this.initGame();
	}

	public void setSquareVal(int position, SquareVal val) {
		Square s = getSquare(position);
		if(s == null) {
			System.err.print("ERROR");
			return;
		}
		s.setValue(val);
	}
	
	public SquareVal getSquareVal(int position) {
		Square s = getSquare(position);	
		if(s == null) {
			System.err.print("ERROR");
			return null;
		}
		return s.getValue();
	}

	public Square getSquare(int position) {
		if(position < 1 || position > BOARD_SIZE) {
			throw new IllegalArgumentException("Position out of bounds " + position);
		}
		for(Square s : this.squares) {
			if(s.getPosition() == position) {
				return s;
			}
		}
		return null;
	}
	
	private void initGame() {
		for(int i = 0; i < BOARD_SIZE; i++) {
			this.squares.add(new Square(SquareVal.BLANK, i+1));
		}
	}
	
	public String checkWinner() {
		int[][] win = {{0, 1, 2}, 
					   {0, 4, 8},
					   {3, 4, 5},
					   {6, 7, 8},
					   {0, 3, 6},
					   {2, 5, 8},
					   {1, 4, 7},
					   {2, 4, 6}};
		
		for(int [] winCombo : win) {
			if((squares.get(winCombo[0]).getValue() != SquareVal.BLANK) &&
			   (squares.get(winCombo[0]).equals(squares.get(winCombo[1])) && 
			    squares.get(winCombo[0]).equals(squares.get(winCombo[2])))) {
					return squares.get(winCombo[0]).getStringValue() + " wins";
				}
		}
		
		for(Square s : squares) {
			if(s.getValue() == SquareVal.BLANK) {
				return null;
			}
		}
		return "Tie Game";
	}
}