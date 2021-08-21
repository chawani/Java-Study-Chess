package chess.piece;

import chess.board.Position;

public class King extends PieceImpl{
	private boolean validCastling=true;
	public King(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♔":"♚";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMovableArea(Position startPosition,Position endPosition) {
		if(Math.abs(endPosition.getX() - startPosition.getX())>1
				||Math.abs(endPosition.getY() -startPosition.getY())>1)
			return false;
		return true;
	}
	@Override
	public void moveTo(Position position) {
		super.moveTo(position);
		validCastling=false;
	}
	public boolean getValidCastling() {
		return validCastling;
	}
}
