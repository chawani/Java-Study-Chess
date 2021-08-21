package chess;

import java.util.Scanner;

import chess.board.Position;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.player.Player;

public class Rule {
	private Piece[][] squares;
	
	private Rule(Piece[][] squares) {
		this.squares=squares;
	}
	
	public static Rule settingBoardSituation(Piece[][] squares) {
		return new Rule(squares);
	}
	
	public boolean checkPieceMovable(Position startPosition,Position endPosition) {
		Piece currentPiece=squares[startPosition.getY()][startPosition.getX()];
		Piece destination=squares[endPosition.getY()][endPosition.getX()];
		if (destination == null) {
			if (currentPiece.isMovableArea(startPosition, endPosition))
				return true;
			return false;
		}
		if (destination.getColor() != currentPiece.getColor())
			if (currentPiece.isMovableArea(startPosition, endPosition))
				return true;
		return false;
	}
	
	public boolean checkPromotion(Piece piece) {
		if (piece.getEmoji().equals("♙") || piece.getEmoji().equals("♟")) {
			Pawn pawn = (Pawn) piece;
			if (pawn.canPromotion())
				return true;
		}
		return false;
	}
	
	public Piece pawnPromotion(Piece piece) {
		Pawn pawn=(Pawn)piece;
		System.out.println("Pawn을 무엇으로 바꾸시겠습니까?(퀸,룩,비숍,나이트로 가능)");
		Scanner scan=new Scanner(System.in);
		String convertTo=scan.nextLine();
		return pawn.promotion(convertTo);
	}
	
	public void askCastling(Player player) {
		Castling castling=Castling.settingCastling(player, squares);
		if(castling.checkPossible()) {
			castling.move();
			squares=castling.getBoardSquares();
		}
	}
	
	public boolean checkEnPassant(Piece myPawn,Piece opponentPawn) {
		if(opponentPawn==null) return false;
		if(myPawn.getColor()==opponentPawn.getColor())
			return false;
		Position myPawnPosition=myPawn.getPosition();
		Position oppoPawnPosition=opponentPawn.getPosition();
		int y=(myPawn.getColor().equals("white"))?3:4;
		if (oppoPawnPosition.getY() == y && myPawnPosition.getY() == y
				&& Math.abs(oppoPawnPosition.getX() - myPawnPosition.getX()) == 1) {
			return true;
		}
		return false;
	}
}
