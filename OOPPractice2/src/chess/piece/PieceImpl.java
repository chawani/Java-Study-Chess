package chess.piece;

import chess.board.Position;

abstract class PieceImpl implements Piece{
	private String color;
	private Position position;
	private String emoji;
	
	public PieceImpl(String color,Position position) {
		this.color=color;
		this.position=position;
	}
	public void setEmoji(String emoji) {
		this.emoji=emoji;
	}
	public String getEmoji() {
		return emoji;
	}
	public String getColor() {
		return color;
	}
	public Position getPosition() {
		return position;
	}
	public void moveTo(Position position) {
		this.position=position;
	}
	abstract public boolean isMovableArea(Position startPosition,Position endPosition);
}
