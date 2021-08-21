package chess.piece;

import chess.board.Position;

public class Queen extends PieceImpl{
	public Queen(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♕":"♛​";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMovableArea(Position startPosition,Position endPosition) {
		boolean bishopArea=true;
		boolean rookArea=true;
		if(startPosition.getX()==endPosition.getX() 
				|| startPosition.getY()==endPosition.getY())
			bishopArea=false;
		if(Math.abs(endPosition.getX() - startPosition.getX()) 
				!= Math.abs(endPosition.getY() - startPosition.getY()))
			bishopArea=false;
		if(Math.abs(endPosition.getX() - startPosition.getX())>0
				&&Math.abs(endPosition.getY() - startPosition.getY())!=0)
			rookArea=false;
		if(Math.abs(endPosition.getY() - startPosition.getY())>0
				&&Math.abs(endPosition.getX() - startPosition.getX())!=0)
			rookArea=false;
		if(bishopArea||rookArea) return true;
		return false;
	}
}
