package chess.piece;

import chess.board.Position;

public interface Piece {
	String getColor();
	Position getPosition();
	String getEmoji();
	void moveTo(Position position);
	abstract boolean isMoveableArea(int startX,int startY,int endX,int endY);
}
