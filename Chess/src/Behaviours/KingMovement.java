package Behaviours;
import Board.ChessBoard;
import Board.Tile;
import ChessGame.Move;
import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class KingMovement implements MovementBehaviour {
    private final int[] row = { 0, 0, 1, 1, 1, -1, -1 ,-1 };
    private final int[] col = { 1, -1, -1, 0, 1, -1, 0, 1 };
    private List<Move> possibleMoves;
    ChessBoard chessBoard;
    public KingMovement(){
        chessBoard=ChessBoard.getInstance();
        possibleMoves = new ArrayList<>();
    }
    @Override
    public List<Move> getPossibleMoves(Tile startingTile, Color color){
        possibleMoves.clear();
        Piece currentKing = startingTile.getPiece();
        for(int i = 0 ; i < 8 ; i ++){
            int possibleXCoordinate = startingTile.getTileXCoordinate() + row[i];
            int possibleYCoordinate = startingTile.getTileYCoordinate() + col[i];
            if(possibleXCoordinate >= 0 && possibleXCoordinate < 8 && possibleYCoordinate >= 0 && possibleYCoordinate < 8){
                Tile possibleEndingTile = chessBoard.getTile(possibleXCoordinate, possibleYCoordinate);
                if(possibleEndingTile.isTileEmpty() || !startingTile.getPiece().getColor().equals(currentKing.getColor()))
                    possibleMoves.add(new Move(startingTile,possibleEndingTile, color));
            }
        }
        return possibleMoves;
    }

}
