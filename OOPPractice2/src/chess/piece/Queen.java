package chess.piece;

import chess.board.Position;

public class Queen extends PieceImpl{
	public Queen(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♕":"♛​";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMoveableArea(int startX,int startY,int endX,int endY) {
		boolean bishopArea=true;
		boolean rookArea=true;
		if(startX==endX || startY == endY)
			bishopArea=false;
		if(Math.abs(endX - startX) != Math.abs(endY - startY))
			bishopArea=false;
		if(Math.abs(endX-startX)>0&&Math.abs(endY-startY)!=0)
			rookArea=false;
		if(Math.abs(endY-startY)>0&&Math.abs(endX-startX)!=0)
			rookArea=false;
		if(bishopArea||rookArea) return true;
		return false;
	}
}
