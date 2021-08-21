package chess.piece;

import chess.board.Position;

public class Rook extends PieceImpl{
	private boolean validCastling=true;
	public Rook(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♖":"♜";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMovableArea(Position startPosition,Position endPosition) {
		if(Math.abs(endPosition.getX() - startPosition.getX())>0
				&&Math.abs(endPosition.getY() - startPosition.getY())!=0)
			return false;
		if(Math.abs(endPosition.getY() - startPosition.getY())>0
				&&Math.abs(endPosition.getX() - startPosition.getX())!=0)
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
