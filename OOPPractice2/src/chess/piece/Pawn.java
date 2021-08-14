package chess.piece;

import chess.board.Position;

public class Pawn extends PieceImpl{
	public Pawn(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♙":"♟​";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMoveableArea(int startX,int startY,int endX,int endY) {
		int move=(super.getColor().equals("white"))?-1:1;
		if(Math.abs(endX-startX)!=0) 
			return false;
		if(endY-startY!=move)
			return false;
		return true;
	}
	public boolean canPromotion() {
		Position position=super.getPosition();
		if(super.getColor().equals("white")) {
			if(position.getY()==0)
				return true;
		}
		if(position.getY()==7)
			return true;
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
