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
            squares[6][x]=new Pawn("white",new Position(1,x));
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
	
	public boolean movePiece(int startX,int startY,int endX,int endY) {
		Piece piece=squares[startY][startX];
		if(piece.isMoveableArea(startX, startY, endX, endY)
				&&!isPlaceMyPiece(piece,endX,endY)) {
			piece.moveTo(new Position(endY,endX));
			squares[endY][endX]=piece;
			squares[startY][startX]=null;
			if(checkPromotion(piece))
				squares[endY][endX]=rule.pawnPromotion(piece);
			return true;
		}
		System.out.println("이곳에는 말을 둘 수 없습니다.");
		return false;
	}
	
	public boolean isPlaceMyPiece(Piece piece,int endX,int endY){
		if(squares[endY][endX]!=null
				&&piece.getColor()==squares[endY][endX].getColor()) return true;
		return false;
	}
	
	public boolean checkPromotion(Piece piece) {
		if(piece.getEmoji().equals("♙")||piece.getEmoji().equals("♟")) {
				Pawn pawn=(Pawn)piece;
				if(pawn.canPromotion()) return true;
			}
		return false;
	}
	
	public void checkCastling(Player player) {
		Scanner scan=new Scanner(System.in);
		boolean queenSide=false,kingSide=false;
		String sideSelect="";
		String color=player.getPieceColor();
		int y=color.equals("white")?7:0;
		
		King king=(King)squares[y][4];
		Rook rook1=(Rook)squares[y][0];
		Rook rook2=(Rook)squares[y][7];
		
		queenSide=checkQueenSideCastling(king,rook1);
		kingSide=checkKingSideCastling(king,rook2);
		
		if(queenSide) {
			System.out.println("퀸사이드로 캐슬링하시려면 퀸이라고 입력해주세요");
			
		}
		if(kingSide) {
			System.out.println("킹사이드로 캐슬링하시려면 킹이라고 입력해주세요");
		}
		
		sideSelect=scan.nextLine();
		if(sideSelect.equals("퀸")) {
			squares[y][2]=king;
			squares[y][4]=null;
			squares[y][3]=rook1;
			squares[y][0]=null;
		}
		if(sideSelect.equals("킹")) {
			squares[y][6]=king;
			squares[y][4]=null;
			squares[y][5]=rook2;
			squares[y][7]=null;
		}
	}
	
	public boolean checkKingSideCastling(King king,Rook rook) {
		int y=(king.getColor().equals("white"))?7:0;
		for(int i=5;i<7;i++) {
			if(squares[y][i]!=null) return false;
		}
		if(!king.getValidCastling()||!rook.getValidCastling()) return false;
		return true;
	}
	
	public boolean checkQueenSideCastling(King king,Rook rook) {
		int y=(king.getColor().equals("white"))?7:0;
		for(int i=1;i<4;i++) {
			if(squares[y][i]!=null) return false;
		}
		if(!king.getValidCastling()||!rook.getValidCastling()) return false;
		return true;
	}
	
	public boolean checkEnPassant(Pawn opponentPawn,Pawn myPawn) {
		Position myPawnPosition=myPawn.getPosition();
		Position oppoPawnPosition=opponentPawn.getPosition();
		if(myPawn.getColor().equals("white")) {
			if(oppoPawnPosition.getY()==3&&myPawnPosition.getY()==3
					&&Math.abs(oppoPawnPosition.getX()-myPawnPosition.getX())==1)
			{
				squares[oppoPawnPosition.getY()-1][oppoPawnPosition.getX()]=myPawn;
				myPawn.moveTo(oppoPawnPosition);
				squares[myPawnPosition.getY()][myPawnPosition.getX()]=null;
				squares[oppoPawnPosition.getY()][oppoPawnPosition.getX()]=null;
			}
		}	
		return false;
	}
}
