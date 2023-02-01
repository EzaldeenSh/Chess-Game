package Behaviours;

import Board.ChessBoard;
import Board.Tile;
import ChessGame.Move;
import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RookMovement implements MovementBehaviour {
    private List<Move> possibleMoves;
    ChessBoard chessBoard;
    public RookMovement(){
        chessBoard = ChessBoard.getInstance();
        possibleMoves = new ArrayList<>();
    }
    @Override
    public List<Move> getPossibleMoves(Tile startingTile, Color color) {
        possibleMoves.clear();
        if(!color.equals(startingTile.getPiece().getColor())){
            return possibleMoves;
        }

        Piece currentRook = startingTile.getPiece();

        Tile possibleEndingTile;
        boolean blocked = false;
        for(int i = startingTile.getTileXCoordinate() + 1 ; i < 8 ; i++){
            possibleEndingTile = chessBoard.getTile(i, startingTile.getTileYCoordinate());
            if(blocked)
                break;
            if(possibleEndingTile.isTileEmpty())
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            else if(!possibleEndingTile.getPiece().getColor().equals(currentRook.getColor())){
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                blocked = true;
            }else if(possibleEndingTile.getPiece().getColor() .equals( currentRook.getColor())){
                blocked  = true;
            }
        }
        blocked = false;
        for(int i = startingTile.getTileXCoordinate() - 1; i >= 0 ; i--) {
            possibleEndingTile = chessBoard.getTile(i, startingTile.getTileYCoordinate());
            if (blocked)
                break;
            if (possibleEndingTile.isTileEmpty()) {
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            } else if (!possibleEndingTile.getPiece().getColor().equals(currentRook.getColor())) {
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                blocked = true;
            }else if(possibleEndingTile.getPiece().getColor().equals(currentRook.getColor())){
                blocked  = true;
            }
        }
        blocked = false;
        for(int i = startingTile.getTileYCoordinate() + 1 ; i < 8 ; i++){
            possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate(), i);
            if(blocked)
                break;
            if(possibleEndingTile.isTileEmpty())
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            else if(!possibleEndingTile.getPiece().getColor().equals(currentRook.getColor())) {
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                blocked = true;
            }else if(possibleEndingTile.getPiece().getColor().equals(currentRook.getColor())){
                blocked  = true;
            }
        }
        blocked = false;
        for(int i = startingTile.getTileYCoordinate() - 1 ; i >= 0 ; i--) {
            possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate(), i);
            if (blocked)
                break;
            if (possibleEndingTile.isTileEmpty())
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            else if (!possibleEndingTile.getPiece().getColor().equals(currentRook.getColor())) {
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                blocked = true;
            }else if(possibleEndingTile.getPiece().getColor().equals(currentRook.getColor())){
                blocked  = true;
            }
        }
        return possibleMoves;
    }
}
