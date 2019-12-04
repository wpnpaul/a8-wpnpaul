package gameoflife;

public class LifeModel {

	private int[][] _currBoard;
	private int[][] _nextBoard;
	
	public LifeModel() {
		
		_currBoard = new int[10][10];
		_nextBoard = new int[10][10];
	}
	
	public int[][] getCurrGen() {
		return _currBoard;
	}
	
	public int[][] getNextGen() {
		return _nextBoard;
	}
	
	public void resizeBoard(int size) {
		int[][] temp = new int[size][size];
		
		for (int i = 0; i < _nextBoard.length; i++) {
			for (int j = 0; j < _nextBoard[0].length; j++) {
				if (_nextBoard[i][j] == 1) {
					temp[i][j] = 1;
				}
			}
		}	
		
		_nextBoard = temp;
	}
	
	public void toggleSpot(int x, int y) {
		if (_nextBoard[x][y] == 0) {
			_nextBoard[x][y] = 1;
		} else {
			_nextBoard[x][y] = 0;
		}
	}
	
	public void clearBoard() {
		for (int i = 0; i < _nextBoard.length; i++) {
			for (int j = 0; j < _nextBoard[0].length; j++) {
				_nextBoard[i][j] = 0;
			}
		}	
	}
	
	public void fillBoard() {
		for (int i = 0; i < _nextBoard.length; i++) {
			for (int j = 0; j < _nextBoard[0].length; j++) {
				_nextBoard[i][j] = (int)(Math.random() * 2);
			}
		}	
	}
	
	public void nextGen() {
		for (int i = 0; i < _nextBoard.length; i++) {
			for (int j = 0; j < _nextBoard[0].length; j++) {
				int neighbors = getNeighbors(i, j);
				int num = _nextBoard[i][j];
				if (num == 0 && neighbors == 3) {
					_nextBoard[i][j] = 1;
				} else if (num == 1 && (neighbors < 2 || neighbors > 3)) {
					_nextBoard[i][j] = 0;
				} else {
					_nextBoard[i][j] = num; 
				}
			}
		}
	}
	
	private int getNeighbors(int x, int y) {
		int sum = 0;
		
		if (x != 0 && y != 0) {
			sum += _nextBoard[x - 1][y - 1];
		}
		if (y != 0) {
			sum += _nextBoard[x][y - 1];
		}
		if (x != 0) {
			sum += _nextBoard[x - 1][y];
		}
		if (x != _nextBoard.length - 1 && y != _nextBoard[0].length - 1) {
			sum += _nextBoard[x + 1][y + 1];
		}
		if (y != _nextBoard[0].length - 1) {
			sum += _nextBoard[x][y + 1];
		}
		if (x != _nextBoard.length - 1) {
			sum += _nextBoard[x + 1][y];
		}
		if (x != _nextBoard.length - 1 && y != 0) {
			sum += _nextBoard[x + 1][y - 1];
		}
		if (x != 0 && y != _nextBoard[0].length - 1) {
		sum += _nextBoard[x - 1][y + 1];
		}
		
		return sum;
	}
	
	public void printBoard() {
		System.out.println("");
		for (int i = 0; i < _nextBoard.length; i++) {
			for (int j = 0; j < _nextBoard[0].length; j++) {
				System.out.print(_nextBoard[i][j]);
			}
			System.out.println("");
		}
	}
}
