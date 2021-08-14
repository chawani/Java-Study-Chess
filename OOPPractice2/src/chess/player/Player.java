package chess.player;

public class Player {
	String name;
	String pieceColor;
	
	private Player(String name) {
		this.name=name;
	}
	public static Player createByName(String name) {
		return new Player(name);
	}
	public void setPieceColor(String color) {
		this.pieceColor=color;
	}
	public String getPieceColor() {
		return pieceColor;
	}
	public String getName() {
		return name;
	}
}
