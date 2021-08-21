package chess.piece;

import chess.board.Position;

public class Pawn extends PieceImpl{
	private boolean checkMove=false;
	public Pawn(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♙":"♟​";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMovableArea(Position startPosition,Position endPosition) {
		int move=(super.getColor().equals("white"))?-1:1;
		if(checkMove==false)
			move=(super.getColor().equals("white"))?-2:2;
		if(Math.abs(endPosition.getX() - startPosition.getX())!=0) 
			return false;
		if(endPosition.getY() - startPosition.getY()!=move)
			return false;
		checkMove=true;
		return true;
	}
	public boolean canPromotion() {
		Position position = super.getPosition();
		if (super.getColor().equals("white")) {
			if (position.getY() == 1)
				return true;
		}
		if (super.getColor().equals("black")) {
			if (position.getY() == 6)
				return true;
		}
		return false;
	}
	public Piece promotion(String convertTo) {
		String color=super.getColor();
		Position position=super.getPosition();
		if(convertTo.equals("퀸")) {
			return new Queen(color,position);
		}
		if(convertTo.equals("룩")) {
			return new Rook(color,position);
		}
		if(convertTo.equals("비숍")) {
			return new Bishop(color,position);
		}
		if(convertTo.equals("나이트")) {
			return new Knight(color,position);
		}
		return this;
	}
}
