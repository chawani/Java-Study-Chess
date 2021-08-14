package chess;

import java.util.Scanner;

import chess.board.Position;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;

public class Rule {
	private Piece[][] squares;
	
	private Rule(Piece[][] squares) {
		this.squares=squares;
	}
	
	public static Rule settingBoardSituation(Piece[][] squares) {
		return new Rule(squares);
	}
	
	public Piece pawnPromotion(Piece piece) {
		Pawn pawn=(Pawn)piece;
		Position position=piece.getPosition();
		System.out.println("Pawn을 무엇으로 바꾸시겠습니까?(퀸,룩,비숍,나이트로 가능)");
		Scanner scan=new Scanner(System.in);
		String convertTo=scan.nextLine();
		return pawn.promotion(convertTo);
	}
}
