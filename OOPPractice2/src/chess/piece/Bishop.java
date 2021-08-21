package chess.piece;

import chess.board.Chessboard;
import chess.board.Position;

public class Bishop extends PieceImpl{
	public Bishop(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♗":"♝";
		super.setEmoji(emoji);
	}
	
	@Override
	public boolean isMovableArea(Position startPosition,Position endPosition) {
		if(startPosition.getX()==endPosition.getX() || startPosition.getY()==endPosition.getY())
			return false;
		if(Math.abs(endPosition.getX() - startPosition.getX()) 
				!= Math.abs(endPosition.getY() - startPosition.getY()))
			return false;
		return true;
	}
	
}
