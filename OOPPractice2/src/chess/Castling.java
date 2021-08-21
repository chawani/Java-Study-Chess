package chess;

import java.util.Scanner;

import chess.piece.King;
import chess.piece.Piece;
import chess.piece.Rook;
import chess.player.Player;

public class Castling {
	private Player player;
	private Piece[][] squares;
	private int y;
	private boolean queenSidePossible,kingSidePossible=false;
	private King king;
	private Rook rook1,rook2;
	
	private Castling(Player player,Piece[][] squares) {
		this.player=player;
		String color=player.getPieceColor();
		y=color.equals("white")?7:0;
		king=(King)squares[y][4];
		rook1=(Rook)squares[y][0];
		rook2=(Rook)squares[y][7];
		this.squares=squares;
	}
	
	public static Castling settingCastling(Player player,Piece[][] squares) {
		return new Castling(player,squares);
	}
	
	public boolean checkPossible() {
		queenSidePossible=checkQueenSide(king,rook1);
		kingSidePossible=checkKingSide(king,rook2);
		if(queenSidePossible||kingSidePossible) {
			return true;
		}
		return false;
	}
	
	public boolean checkKingSide(King king,Rook rook) {
		int y=(king.getColor().equals("white"))?7:0;
		for(int i=5;i<7;i++) {
			if(squares[y][i]!=null) return false;
		}
		if(!king.getValidCastling()||!rook.getValidCastling()) return false;
		return true;
	}
	
	public boolean checkQueenSide(King king,Rook rook) {
		int y=(king.getColor().equals("white"))?7:0;
		for(int i=1;i<4;i++) {
			if(squares[y][i]!=null) return false;
		}
		if(!king.getValidCastling()||!rook.getValidCastling()) return false;
		return true;
	}
	
	public void move() {
		while (true) {
			if (queenSidePossible)
				System.out.println("�����̵�� ĳ�����Ͻ÷��� ���̶�� �Է����ּ���");
			if (kingSidePossible)
				System.out.println("ŷ���̵�� ĳ�����Ͻ÷��� ŷ�̶�� �Է����ּ���");
			Scanner scan = new Scanner(System.in);
			String sideSelect = scan.nextLine();
			if (sideSelect.equals("��")) {
				squares[y][2] = king;
				squares[y][4] = null;
				squares[y][3] = rook1;
				squares[y][0] = null;
				return;
			}
			if (sideSelect.equals("ŷ")) {
				squares[y][6] = king;
				squares[y][4] = null;
				squares[y][5] = rook2;
				squares[y][7] = null;
				return;
			}
		}
	}
	
	public Piece[][] getBoardSquares(){
		return squares;
	}
}
