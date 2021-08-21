package chess.piece;

import chess.board.Position;

public class Knight extends PieceImpl{
	public Knight(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♘":"♞";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMovableArea(Position startPosition,Position endPosition) {
		if(Math.abs(endPosition.getX() - startPosition.getX()) == 2 
				&& Math.abs(endPosition.getY() - startPosition.getY()) == 1)
			return true;
		if(Math.abs(endPosition.getX() - startPosition.getX()) == 1 
				&& Math.abs(endPosition.getY() - startPosition.getY()) == 2)
			return true;
		return false;
	}
}
