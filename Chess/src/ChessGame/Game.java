package ChessGame;
import Board.ChessBoard;
import java.awt.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game{
    ChessBoard chessBoard;
    private static Game instance = null;
    private final boolean GAME_IS_RUNNING = true;
    private final int END_GAME_TIMER = 50;
    private Game(){
        chessBoard = ChessBoard.getInstance();
    }
    public static Game getInstance(){
        if(instance == null)
            instance = new Game();
        return instance;
    }
    private boolean useRegex(final String input) {
        final Pattern pattern = Pattern.compile("move [a-h][1-8]\\s[a-h][1-8]", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public void start(){
        chessBoard.initiateTiles();
        chessBoard.initiateSides();
        Player player1;
        Player player2;
        String playerInput;
        int counter = 0;
        Move currentMove;
        Scanner gameScanner = new Scanner(System.in);
        System.out.println("Enter White Player Name: ");
        String player1Name = gameScanner.nextLine();
        player1 = new Player(player1Name, Color.WHITE);
        System.out.println("Enter Black Player Name: ");
        String player2Name = gameScanner.nextLine();
        player2 = new Player(player2Name, Color.BLACK);
        while(GAME_IS_RUNNING){
            if(counter == END_GAME_TIMER){
                System.out.println("The game is over and the result is draw by timer !)");
                break;}
            if(chessBoard.isBlackWon()){
                System.out.println("The game is over and "+ player2Name+" won!");
                break;
            }
            if(chessBoard.isWhiteWon()){
                System.out.println("The game is over and "+ player1Name+" won!");
                break;
            }
            if(chessBoard.getAliveBlackPieces() == 1 && chessBoard.getAliveWhitePieces()== 1){
                System.out.println("The game is over and the result is draw by two kings");
                break;
            }
            if(chessBoard.isStalemate()){
                System.out.println("The game is over and the result is draw by stalemate");
                break;
            }
            if(counter %2 == 0) {
                System.out.println("Enter the next move (" + player1Name + "):");
                playerInput = gameScanner.nextLine();
                if(useRegex(playerInput)) {
                    currentMove = chessBoard.translateMove(playerInput, player1.getColor());
                    if(!chessBoard.makeMove(currentMove.startingTile, currentMove.endingTile, player1.getColor())){
                        System.out.println("That move was invalid!");
                        continue;
                    }
                } else{
                    System.out.println("That move was invalid!");
                continue;}
            } else {
                System.out.println("Enter the next move (" + player2Name + "):");
                playerInput = gameScanner.nextLine();
                if(useRegex(playerInput)) {
                    currentMove = chessBoard.translateMove(playerInput, player2.getColor());
                    if(!chessBoard.makeMove(currentMove.startingTile, currentMove.endingTile, player2.getColor())){
                        System.out.println("That move was invalid!");
                        continue;
                    }
                } else {
                    System.out.println("That move was invalid!");
                continue;
                }
            }
            counter++;
            System.out.println("Number of played turns: "+counter);
        }
    }
}