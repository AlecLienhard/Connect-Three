package cs110.project3;

import javafx.application.Application;
import static cs110.project3.ConnectThree.Player.*;

public class Sample extends ConnectThree {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	/**	Not only does this win detector only check for
	 * vertical wins (ignoring horizontal and diagonal
	 * wins) but it does so inefficiently by trying all
	 * possible vertical wins instead of using the 
	 * row and column hints to limit its search.*/
	@Override
	protected Player getWinner(Player[][] state, int rowHint, int columnHint) {
		for (int r = 0; r <= GRID_ROWS-MATCH_COUNT; r++) {
			for (int c = 0; c < GRID_COLUMNS; c++) {
				if (
					state[r][c] != NONE
					&& state[r][c] == state[r+1][c]
					&& state[r+1][c] == state[r+2][c]
				) return state[r][c];
			}
		}
		
		return NONE;
	}
	
	@Override
	protected void registerOriginalAIs() {
		registerAI(new Lefty());
	}
	
	/** 
	 * This AI picks the leftmost free column
	 * without any strategy.
	 */
	private class Lefty extends AI {
		// TODO: remove this AI from your implementation
		
		@Override
		public int getBestColumn(Player[][] state, Player player) {
			for (int c: range(0, GRID_COLUMNS-1))
				if (free(state, c) >= 0)
					return c;
			return -1;
		}
	}
}
