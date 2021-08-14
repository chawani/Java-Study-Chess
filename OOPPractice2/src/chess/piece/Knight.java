package chess.piece;

import chess.board.Position;

public class Knight extends PieceImpl{
	public Knight(String color,Position position) {
		super(color,position);
		String emoji=(color.equals("white"))?"♘":"♞";
		super.setEmoji(emoji);
	}
	@Override
	public boolean isMoveableArea(int startX,int startY,int endX,int endY) {
		if(Math.abs(endX - startX) == 2 && Math.abs(endY - startY) == 1)
			return true;
		if(Math.abs(endX-startX) == 1 && Math.abs(endY - startY) == 2)
			return true;
		return false;
	}
}
