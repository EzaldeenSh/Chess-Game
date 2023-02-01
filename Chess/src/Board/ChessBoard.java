package Board;
import ChessGame.Move;
import Pieces.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ChessBoard{
    private final int[] whiteKingLocation;
    private final int[] blackKingLocation;
    private boolean blackWon = false;
    private boolean whiteKingMoved = false;
    private boolean blackKingMoved = false;


    private boolean leftWhiteRookMoved = false;
    private boolean rightWhiteRookMoved = false;
    private boolean leftBlackRookMoved = false;
    private boolean rightBlackRookMoved = false;


    private boolean whiteWon = false;
    private boolean isWhiteChecked = false;
    private boolean isBlackChecked = false;
    private static ChessBoard instance = null;
    private boolean stalemate = false;
    private Tile[][] tiles;
    private final PiecesFactory piecesFactory;
    private int aliveWhitePieces = 16;
    private int aliveBlackPieces = 16;
    private final List<Move> possibleWhiteMovements;
    private final List<Move> possibleBlackMovements;
    private ChessBoard(){
        whiteKingLocation = new int[]{4, 0};
        blackKingLocation = new int[]{4, 7};
        piecesFactory = new PiecesFactory();
        possibleWhiteMovements = new ArrayList<>();
        possibleBlackMovements = new ArrayList<>();

    }
    public static ChessBoard getInstance(){
        if (instance == null)
            instance = new ChessBoard();
        return instance;
    }

    public boolean isStalemate() {
        return stalemate;
    }
    public int getAliveWhitePieces() {
        return aliveWhitePieces;
    }
    public int getAliveBlackPieces() {
        return aliveBlackPieces;
    }
    public boolean isBlackWon() {
        return blackWon;
    }
    public boolean isWhiteWon() {
        return whiteWon;
    }
    public void initiateTiles(){
        tiles = new Tile[8][8];
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                tiles[i][j] = new Tile(i, j, null);
            }
        }
    }
    public void initiateSides(){
        for(int i = 0 ; i < 8 ; i++){
            tiles[i][1].setPiece(piecesFactory.createPiece("Pawn", Color.WHITE));
        }
        tiles[0][0].setPiece(piecesFactory.createPiece("Rook", Color.WHITE));
        tiles[1][0].setPiece(piecesFactory.createPiece("Knight", Color.WHITE));
        tiles[2][0].setPiece(piecesFactory.createPiece("Bishop", Color.WHITE));

        tiles[3][0].setPiece(piecesFactory.createPiece("Queen", Color.WHITE));
        tiles[4][0].setPiece(piecesFactory.createPiece("King", Color.WHITE));

        tiles[5][0].setPiece(piecesFactory.createPiece("Bishop", Color.WHITE));
        tiles[6][0].setPiece(piecesFactory.createPiece("Knight", Color.WHITE));
        tiles[7][0].setPiece(piecesFactory.createPiece("Rook", Color.WHITE));

        for(int i = 0 ; i < 8 ; i++){
            tiles[i][6].setPiece(piecesFactory.createPiece("Pawn", Color.BLACK));
        }

        tiles[0][7].setPiece(piecesFactory.createPiece("Rook", Color.BLACK));
        tiles[1][7].setPiece(piecesFactory.createPiece("Knight", Color.BLACK));
        tiles[2][7].setPiece(piecesFactory.createPiece("Bishop", Color.BLACK));

        tiles[3][7].setPiece(piecesFactory.createPiece("Queen", Color.BLACK));
        tiles[4][7].setPiece(piecesFactory.createPiece("King", Color.BLACK));

        tiles[5][7].setPiece(piecesFactory.createPiece("Bishop", Color.BLACK));
        tiles[6][7].setPiece(piecesFactory.createPiece("Knight", Color.BLACK));
        tiles[7][7].setPiece(piecesFactory.createPiece("Rook", Color.BLACK));
    }
    public Move translateMove(String moveInput, Color color){
        int startX = moveInput.charAt(5) - 97;
        int startY = (int)moveInput.charAt(6) - 49;
        int endX = moveInput.charAt(8) - 97;
        int endY = (int)moveInput.charAt(9) - 49;
        return new Move(getTile(startX, startY), getTile(endX, endY),color);
    }
    public Tile getTile(int xCoordinate, int yCoordinate){
        if(xCoordinate > 7 || yCoordinate > 7 || xCoordinate < 0 || yCoordinate < 0)
            throw new IllegalArgumentException();
        return tiles[xCoordinate][yCoordinate];
    }

    private void updateBlackKingLocation(Tile endLocation){
        blackKingLocation[0] = endLocation.getTileXCoordinate();
        blackKingLocation[1] = endLocation.getTileYCoordinate();
        blackKingMoved = true;
    }
    private void updateWhiteKingLocation(Tile endLocation){
        whiteKingLocation[0] = endLocation.getTileXCoordinate();
        whiteKingLocation[1] = endLocation.getTileYCoordinate();
        whiteKingMoved = true;
    }
    private boolean isTileSafeFrom(Tile tile, Color color){
        if(color.equals(Color.WHITE)){
            for(Move m : possibleWhiteMovements){
                if(m.getEndingTile().equals(tile))
                    return false;
            }
        }
        else if(color.equals(Color.BLACK)){
            for(Move m : possibleBlackMovements){
                if(m.getEndingTile().equals(tile))
                    return false;
            }
        }
        return true;
    }

    private boolean makeCastling(Tile from, Tile to, Color color){
        Tile tile1;
        Tile tile2;
        Tile tile3;
        if(color.equals(Color.WHITE)) {
            if (to.equals(getTile(6, 0))) {
                tile1 = getTile(5,0);
                tile2 = getTile(6,0);
                if(isTileSafeFrom(tile1, Color.BLACK) && isTileSafeFrom(tile2, Color.BLACK)){
                    if(!whiteKingMoved && !rightWhiteRookMoved){
                        from.removePiece();
                        to.removePiece();
                        to.setPiece(piecesFactory.createPiece("King", Color.WHITE));
                        getTile(5,0).setPiece(piecesFactory.createPiece("Rook", Color.WHITE));
                        updateWhiteKingLocation(to);
                        System.out.println(" White Right Castling Done");
                        rightWhiteRookMoved = true;
                        return true;
                }}
            } else if(to.equals(getTile(2, 0))) {
                tile1 = getTile(1, 0);
                tile2 = getTile(2,0);
                tile3 = getTile(3,0);
                if(isTileSafeFrom(tile1, Color.BLACK) && isTileSafeFrom(tile2, Color.BLACK) && isTileSafeFrom(tile3, Color.BLACK)){
                    if(!whiteKingMoved && !leftWhiteRookMoved){
                        from.removePiece();
                        to.removePiece();
                        to.setPiece(piecesFactory.createPiece("King", Color.WHITE));
                        getTile(3,0).setPiece(piecesFactory.createPiece("Rook", Color.WHITE));
                        updateWhiteKingLocation(to);
                        System.out.println("White Left Castling Done");
                        leftWhiteRookMoved = true;
                        return true;
                    }
                }
            }
        }else if(color.equals(Color.BLACK)){
            if (to.equals(getTile(6, 7))) {
                tile1 = getTile(5,7);
                tile2 = getTile(6,7);
                if(isTileSafeFrom(tile1, Color.WHITE) && isTileSafeFrom(tile2, Color.WHITE)){
                    if(!blackKingMoved && !rightBlackRookMoved){
                        from.removePiece();
                        to.removePiece();
                        to.setPiece(piecesFactory.createPiece("King", Color.BLACK));
                        getTile(5,7).setPiece(piecesFactory.createPiece("Rook", Color.BLACK));
                        updateBlackKingLocation(to);
                        System.out.println(" Black Right Castling Done");
                        rightBlackRookMoved = true;
                        return true;
                    }
                }
            } else if(to.equals(getTile(2, 7))) {
                tile1 = getTile(1,7);
                tile2 = getTile(2,7);
                tile3 = getTile(3,7);
                if(isTileSafeFrom(tile1, Color.WHITE) && isTileSafeFrom(tile2, Color.WHITE) && isTileSafeFrom(tile3, Color.WHITE)){
                    if(!blackKingMoved && !leftBlackRookMoved){
                        from.removePiece();
                        to.removePiece();
                        to.setPiece(piecesFactory.createPiece("King", Color.BLACK));
                        getTile(3,0).setPiece(piecesFactory.createPiece("Rook", Color.BLACK));
                        System.out.println("Black Left Castling Done");
                        leftBlackRookMoved = true;
                        updateBlackKingLocation(to);
                        return true;
                    }
                }
            }
        }
    return false;
    }
    public boolean makeMove(Tile from, Tile to, Color color){
        Piece piece = from.getPiece();
        if(piece instanceof King){
            if(piece.getColor().equals(Color.WHITE)){
                if(!isWhiteChecked){
                    if(from.equals(getTile(4, 0))){
                     if(to.equals(getTile(6, 0))
                            && getTile(7,0).getPiece().getColor().equals(Color.WHITE)
                            && getTile(7,0).getPiece() instanceof Rook
                            || to.equals(getTile(2,0))
                            && getTile(0,0).getPiece().getColor().equals(Color.WHITE)
                            && getTile(0,0).getPiece() instanceof Rook){
                        return makeCastling(from, to, color);
                    }
               }
            } }else if(piece.getColor().equals(Color.BLACK)){
                if(!isBlackChecked){
                if(from.equals(getTile(4,7))){
                    if(to.equals(getTile(6, 7))
                    && getTile(7,7).getPiece().getColor().equals(Color.BLACK)
                    && getTile(7,7).getPiece() instanceof Rook
                    || to.equals(getTile(2, 7))
                    && getTile(0,7).getPiece().getColor().equals(Color.BLACK)
                    && getTile(0,7).getPiece() instanceof Rook){
                        return makeCastling(from, to, color);
                    }
                }
                }
            }
        }
        if(piece== null)
            return false;
        Color pieceColor = piece.getColor();
        List<Move> availableMoves = piece.getPossibleMoves(from, color);
        boolean isMoveFound =false;
        for(Move m : availableMoves){
            if(m.equals(new Move(from, to, color))){
                isMoveFound = true;
                break;
            }
        }
        if(!isMoveFound)
            return false;
        if(piece instanceof King){
            if(pieceColor == Color.BLACK) {
                updateBlackKingLocation(to);
            }
            else {
                updateWhiteKingLocation(to);
            }
        }


        Piece takenPiece = to.getPiece();
        if(takenPiece != null){
            if(takenPiece.getColor().equals(Color.WHITE)){
                aliveWhitePieces--;
            } else if(takenPiece.getColor().equals(Color.BLACK)){
                aliveBlackPieces--;
            }
        }
        if(piece instanceof Rook){
            if(from.equals(getTile(0,0)))
                leftWhiteRookMoved = true;
            if(from.equals(getTile(7,0)))
                rightWhiteRookMoved = true;
            if(from.equals(getTile(0,7)))
                leftBlackRookMoved = true;
            if(from.equals(getTile(7,7)))
                rightBlackRookMoved = true;
        }

        to.setPiece(piecesFactory.createPiece(from.getPiece().toString(),pieceColor));
        from.setPiece(null);
        System.out.println("Moved " + to.getPiece().toString() + " from " +(char)(from.getTileXCoordinate()+97) + (int)(from.getTileYCoordinate()+1) + " to " + (char)(to.getTileXCoordinate()+97) + (int)(to.getTileYCoordinate()+1));

        if(piece instanceof Pawn){
            if(pieceColor.equals(Color.WHITE)){
                if(to.getTileYCoordinate() == 7){
                    //promote White Pawn
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please type your pawn promotion: ");
                    String str = scanner.nextLine();
                    Piece promotedPawn = piecesFactory.createPiece(str, pieceColor);
                    while(promotedPawn == null || promotedPawn instanceof Pawn || promotedPawn instanceof King){
                        System.out.println("Please enter a valid piece!");
                        str = scanner.nextLine();
                        promotedPawn = piecesFactory.createPiece(str, pieceColor);
                    }
                    to.setPiece(promotedPawn);
                }
            }
            else{
                if(to.getTileYCoordinate() == 0){
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Please type your pawn promotion: ");
                    String str = scanner.nextLine();
                    Piece promotedPawn = piecesFactory.createPiece(str, pieceColor);
                    while(promotedPawn == null || promotedPawn instanceof Pawn || promotedPawn instanceof King){
                        System.out.println("Please enter a valid piece!");
                        str = scanner.nextLine();
                        promotedPawn = piecesFactory.createPiece(str, pieceColor);
                    }
                    to.setPiece(promotedPawn);
                }
            }
        }
        possibleWhiteMovements.clear();
        possibleBlackMovements.clear();
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                Tile currentTile = getTile(i, j);
                Piece currentPiece = currentTile.getPiece();
                if(currentPiece!=null){
                    if(currentTile.getPiece().getColor().equals(Color.WHITE))
                        possibleWhiteMovements.addAll(currentPiece.getPossibleMoves(currentTile, Color.WHITE));
                    else if(currentTile.getPiece().getColor().equals(Color.BLACK))
                        possibleBlackMovements.addAll(currentPiece.getPossibleMoves(currentTile, Color.BLACK));
                }
            }
        }
       boolean whiteCheckedLastTurn = isWhiteChecked;
       boolean blackCheckedLastTurn = isBlackChecked;
        for(Move move : possibleWhiteMovements){
            if(move.getEndingTile().getTileXCoordinate() == blackKingLocation[0] && move.getEndingTile().getTileYCoordinate() == blackKingLocation[1]){
            isBlackChecked = true;
                System.out.println("Black King Checked!");
            break;
         }
            isBlackChecked = false;
        }
        if(isBlackChecked && blackCheckedLastTurn)
            whiteWon = true;
        for(Move move : possibleBlackMovements){
            if(move.getEndingTile().getTileXCoordinate() == whiteKingLocation[0] && move.getEndingTile().getTileYCoordinate() == whiteKingLocation[1]){
                isWhiteChecked = true;
                System.out.println("White King Checked");
                break;
            }
            isWhiteChecked = false;
        }
        if(isWhiteChecked && whiteCheckedLastTurn)
            blackWon = true;


        if(color.equals(Color.white)){
            if(!whiteCheckedLastTurn && isWhiteChecked){
                System.out.println("Your king is exposed and the move will be undone!");
                undoMove(from, to, takenPiece);
                return false;
            }
        }
        if(color.equals(Color.BLACK)){
            if(!blackCheckedLastTurn && isBlackChecked){
                System.out.println("Your king is exposed and the move will be undone!");
                undoMove(from, to, takenPiece);
                return false;
            }
        }
        if(takenPiece!= null){
            if(takenPiece.getColor() .equals(Color.WHITE))
                aliveWhitePieces--;
            else if(takenPiece.getColor().equals(Color.BLACK))
                aliveBlackPieces--;
        }
        if(!isWhiteChecked && possibleWhiteMovements.isEmpty() || !isBlackChecked && possibleBlackMovements.isEmpty())
            stalemate = true;
        return true;
    }
    public void undoMove(Tile from, Tile to, Piece takenPiece){
        from.setPiece(to.getPiece());
        to.setPiece(takenPiece);
    }
}