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
	public boolean isMoveableArea(int startX,int startY,int endX,int endY) {
		if(Math.abs(endX-startX)>0&&Math.abs(endY-startY)!=0)
			return false;
		if(Math.abs(endY-startY)>0&&Math.abs(endX-startX)!=0)
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
