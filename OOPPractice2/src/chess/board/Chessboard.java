package chess.board;

import java.util.Scanner;

import chess.Rule;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.player.Player;

public class Chessboard {
	private final int FILE=8;
	private final int RANK=8;
	private Piece[][] squares;
	private Rule rule;
	private String losePlayerKing="";
	
	private Chessboard() {
		this.squares=new Piece[RANK][FILE];
		rule=Rule.settingBoardSituation(squares);
	}
	
	public static Chessboard create() {
		return new Chessboard();
	}
	
	public void setPieces() {
		for (int x = 0; x < 8; x++) {
            squares[1][x]=new Pawn("black",new Position(1,x));
            squares[6][x]=new Pawn("white",new Position(6,x));
        }
        
        squares[0][3]=new Queen("black",new Position(0,3));
        squares[7][3]=new Queen("white",new Position(7,3));
        
        squares[0][4]=new King("black",new Position(0,4));
        squares[7][4]=new King("white",new Position(7,4));

        squares[0][0]=new Rook("black",new Position(0,0));
        squares[0][7]=new Rook("black",new Position(0,7));
        squares[7][0]=new Rook("white",new Position(7,0));
        squares[7][7]=new Rook("white",new Position(7,7));

        squares[0][1]=new Knight("black",new Position(0,1));
        squares[0][6]=new Knight("black",new Position(0,6));
        squares[7][1]=new Knight("white",new Position(7,1));
        squares[7][6]=new Knight("white",new Position(7,6));

        squares[0][2]=new Bishop("black",new Position(0,2));
        squares[0][5]=new Bishop("black",new Position(0,5));
        squares[7][2]=new Bishop("white",new Position(7,2));
        squares[7][5]=new Bishop("white",new Position(7,5));
	}
	
	public Piece[][] getBoard(){
		return squares;
	}
	
	public boolean movePiece(Position startPosition,Position endPosition) {
		Piece currentPiece=squares[startPosition.getY()][startPosition.getX()];
		Piece destination=squares[endPosition.getY()][endPosition.getX()];
		int plusValue=(currentPiece.getColor().equals("white"))?+1:-1;
		Piece enpassantPiece=null;
		if(endPosition.getY()!=0&&endPosition.getY()!=7)
			enpassantPiece=squares[endPosition.getY()+plusValue][endPosition.getX()];
		
		if (rule.checkPieceMovable(startPosition,endPosition)) {
			if(destination!=null)
				if(destination.getClass().getName().equals("King"))
					setKingState((King)destination);
			if (rule.checkPromotion(currentPiece)) {
				squares[endPosition.getY()][endPosition.getX()] = rule.pawnPromotion(currentPiece);
				return true;
			}
			currentPiece.moveTo(endPosition);
			squares[startPosition.getY()][startPosition.getX()] = null;
			squares[endPosition.getY()][endPosition.getX()] = currentPiece;
			return true;
		}
		if(destination==null&&rule.checkEnPassant(currentPiece, enpassantPiece)) {
			currentPiece.moveTo(endPosition);
			squares[startPosition.getY()][startPosition.getX()] = null;
			squares[endPosition.getY()][endPosition.getX()] = currentPiece;
			squares[endPosition.getY()+plusValue][endPosition.getX()]=null;
			return true;
		}
		System.out.println("이곳에는 말을 둘 수 없습니다.");
		return false;
	}
	
	public void setKingState(King king) {
		losePlayerKing=king.getColor();
	}
	
	public String getKingState() {
		return losePlayerKing;
	}
}
