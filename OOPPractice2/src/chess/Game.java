package chess;

import java.util.Scanner;
import java.util.stream.Stream;

import chess.board.Chessboard;
import chess.piece.Piece;
import chess.player.Player;

public class Game {
	Scanner scan;
	Chessboard chessboard;
	Player player1;
	Player player2;
	
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
		printChessboard();
		turn(player1);
		turn(player2);
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
		while(true) {
		System.out.println("옮길말의 위치와 옮길 위치를 입력하세요.(가로,세로 순)");
		String positionInput=scan.nextLine();
		int[] position = Stream.of(positionInput.split(",")).mapToInt(Integer::parseInt).toArray();
		if(chessboard.movePiece(position[0],position[1],position[2],position[3])) break;
		}
		printChessboard();
	}
}
