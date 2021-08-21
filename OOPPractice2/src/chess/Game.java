package chess;

import java.util.Scanner;
import java.util.stream.Stream;

import chess.board.Chessboard;
import chess.board.Position;
import chess.piece.Piece;
import chess.player.Player;

public class Game {
	Scanner scan;
	Chessboard chessboard;
	Player player1;
	Player player2;
	Player winner;
	
	private Game() {
		scan=new Scanner(System.in);
		chessboard=Chessboard.create();
		chessboard.setPieces();
		String[] player=inputPlayerName();
		player1=Player.createByName(player[0]);
		player2=Player.createByName(player[1]);
	}
	
	public static Game setting() {
		return new Game();
	}
	
	public String[] inputPlayerName() {
		System.out.println("당신의 이름과 player2의 이름을 입력하시오");
		String playerNames=scan.nextLine();
		return splitPlayerNames(playerNames);
	}
	
	public String[] splitPlayerNames(String names) {
		return names.split(",");
	}
	
	public void start() {
		selectPlayerPieceColor();
		while (true) {
			if (isGameEnd()) {
				System.out.println(winner.getName()+"님이 이겼습니다");
				break;
			}
			turn(player1);
			turn(player2);
		}
	}
	
	public void selectPlayerPieceColor() {
		player1.setPieceColor("white");
		player2.setPieceColor("black");
		System.out.println("당신의 체스말 색은 흰색입니다.");
	}
	
	public void printChessboard() {
		Piece[][] squares=chessboard.getBoard();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(squares[i][j]==null) {
					System.out.print("⬜ ");
					continue;
				}
				System.out.print(squares[i][j].getEmoji()+" ");
			}
			System.out.println();
		}
	}
	
	public void turn(Player player) {
		Piece[][] squares=chessboard.getBoard();
		printChessboard();
		System.out.println(player.getName()+"님의 차례입니다.");
		while (true) {
			Castling castling=Castling.settingCastling(player, squares);
			if(castling.checkPossible()) {
				System.out.println("캐슬링이 가능합니다.캐슬링 하시겠습니까? 1.예 2.아니오");
				int select=Integer.parseInt(scan.nextLine());
				if(select==1) {
					castling.move();
					break;
				}
				if(select!=2)
					continue;
			}
			System.out.println("옮길말의 위치와 옮길 위치를 입력하세요.(가로,세로 순)");
			String positionInput = scan.nextLine();
			int[] position = Stream.of(positionInput.split(",")).mapToInt(Integer::parseInt).toArray();
			Position startPosition = new Position(position[1], position[0]);
			Position endPosition = new Position(position[3], position[2]);
			if (chessboard.movePiece(startPosition, endPosition)){	
				break;
			}	
		}
	}
	
	
	
	public boolean isGameEnd() {
		if(chessboard.getKingState().equals("white")) {
			winner=player1;
			return true;
		}
		if(chessboard.getKingState().equals("black")) {
			winner=player2;
			return true;
		}
		return false;
	}
}
