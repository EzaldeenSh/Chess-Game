package Behaviours;
import Board.ChessBoard;
import Board.Tile;
import ChessGame.Move;
import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class BishopMovement implements MovementBehaviour {
    private List<Move> possibleMoves;
    ChessBoard chessBoard;
    public BishopMovement(){
        chessBoard = ChessBoard.getInstance();
        possibleMoves = new ArrayList<Move>();
    }
@Override
    public List<Move> getPossibleMoves(Tile startingTile, Color color){
    possibleMoves.clear();
        if(!color.equals(startingTile.getPiece().getColor())){
        return possibleMoves;
    }
        Piece currentBishop = startingTile.getPiece();
        Tile possibleEndingTile;
        boolean blocked = false;
        int secondCounter = 1;
        for(int i = startingTile.getTileXCoordinate() + 1; i < 8 ; i++){
            if(startingTile.getTileYCoordinate() + secondCounter > 7 )
                break;
            possibleEndingTile = chessBoard.getTile(i, startingTile.getTileYCoordinate() + secondCounter);
            if(possibleEndingTile.getTileYCoordinate() > 7 || blocked)
                break;
            if(possibleEndingTile.isTileEmpty())
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            else if(!possibleEndingTile.getPiece().getColor().equals( currentBishop.getColor())){
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                blocked= true;
            } else if(possibleEndingTile.getPiece().getColor().equals(currentBishop.getColor())){
                blocked  = true;
            }
            secondCounter++;
        }
       blocked = false;
       secondCounter = 1;
        for(int i = startingTile.getTileXCoordinate() - 1 ; i >= 0 ; i--){
            if(startingTile.getTileYCoordinate() + secondCounter > 7 )
                break;
            possibleEndingTile = chessBoard.getTile(i, startingTile.getTileYCoordinate() + secondCounter);
            if(possibleEndingTile.getTileYCoordinate() < 0 || blocked)
                break;
        if(possibleEndingTile.isTileEmpty())
            possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
        else if(!possibleEndingTile.getPiece().getColor() .equals( currentBishop.getColor())){
            possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            blocked = true;
        }else if(possibleEndingTile.getPiece().getColor().equals(currentBishop.getColor())){
            blocked  = true;
        }
        secondCounter++;
        }
        blocked = false;
        secondCounter = 1;
        for(int i = startingTile.getTileXCoordinate() + 1 ; i < 8 ; i++){
            if(startingTile.getTileYCoordinate() - secondCounter <0)
                break;
            possibleEndingTile = chessBoard.getTile(i, startingTile.getTileYCoordinate() - secondCounter);
            if(possibleEndingTile.getTileYCoordinate() > 7 || blocked)
                break;
            if(possibleEndingTile.isTileEmpty())
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            else if(!possibleEndingTile.getPiece().getColor() .equals( currentBishop.getColor())){
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                blocked = true;
            }else if(possibleEndingTile.getPiece().getColor().equals(currentBishop.getColor())){
                blocked  = true;
            }
            secondCounter++;
        }
        blocked = false;
        secondCounter = 1;
        for(int i = startingTile.getTileXCoordinate() - 1 ; i >= 0 ; i--){
            if(startingTile.getTileYCoordinate() - secondCounter <0)
                break;
            possibleEndingTile = chessBoard.getTile(i, startingTile.getTileYCoordinate() - secondCounter);
            if(possibleEndingTile.getTileYCoordinate() < 0 || blocked)
                break;
            if(possibleEndingTile.isTileEmpty())
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            else if(!possibleEndingTile.getPiece().getColor() .equals( currentBishop.getColor())){
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                blocked = true;
            }else if(possibleEndingTile.getPiece().getColor().equals(currentBishop.getColor())){
                blocked  = true;
            }
            secondCounter++;

        }
        return possibleMoves;
    }
}