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
	public boolean isMoveableArea(int startX,int startY,int endX,int endY) {
		if(startX==endX || startY == endY)
			return false;
		if(Math.abs(endX - startX) != Math.abs(endY - startY))
			return false;
		return true;
	}
	
}
