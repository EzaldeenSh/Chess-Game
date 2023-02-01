package Behaviours;

import Board.ChessBoard;
import Board.Tile;
import ChessGame.Move;
import Pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PawnMovement implements MovementBehaviour {
    private List<Move> possibleMoves;
    private boolean hasMoved = false;

    ChessBoard chessBoard;
    public PawnMovement(){
        chessBoard = ChessBoard.getInstance();
        possibleMoves = new ArrayList<>();
    }
    @Override
    public List<Move> getPossibleMoves(Tile startingTile, Color color) {
        possibleMoves.clear();
        if(!color.equals(startingTile.getPiece().getColor())){
            return possibleMoves;
        }
        Piece currentPawn = startingTile.getPiece();
        Tile possibleEndingTile;
        if(currentPawn.getColor().equals(Color.WHITE)){
            if(startingTile.getTileYCoordinate() != 1)
                hasMoved = true;
            if(!hasMoved){
                if(chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate()+1).isTileEmpty() && chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate()+2).isTileEmpty()){
                possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate()+2);
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                }
            }
            possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate()+1);
            if(possibleEndingTile.isTileEmpty()){
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            }
            if(startingTile.getTileXCoordinate() > 0){
                possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate() - 1, startingTile.getTileYCoordinate()+1);
                if(!possibleEndingTile.isTileEmpty() && !possibleEndingTile.getPiece().getColor().equals(currentPawn.getColor()))
                    possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            }
            if(startingTile.getTileXCoordinate() < 7){
                possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate() + 1, startingTile.getTileYCoordinate()+1);
                if(!possibleEndingTile.isTileEmpty() && !possibleEndingTile.getPiece().getColor().equals(currentPawn.getColor()))
                    possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            }
        } else {
            if(startingTile.getTileYCoordinate() != 6)
                hasMoved = true;
            if(!hasMoved){
                if(chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate()-1).isTileEmpty() && chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate() - 2).isTileEmpty()){
                    possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate()-2);
                    possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
                }
            }
            possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate(), startingTile.getTileYCoordinate() - 1);
            if(possibleEndingTile.isTileEmpty()){
                possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            }
            if(startingTile.getTileXCoordinate() > 0){
                possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate() - 1, startingTile.getTileYCoordinate()-1);
                if(!possibleEndingTile.isTileEmpty() && !possibleEndingTile.getPiece().getColor().equals(currentPawn.getColor()))
                    possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            }
            if(startingTile.getTileXCoordinate() < 7){
                possibleEndingTile = chessBoard.getTile(startingTile.getTileXCoordinate() + 1, startingTile.getTileYCoordinate()-1);
                if(!possibleEndingTile.isTileEmpty() && !possibleEndingTile.getPiece().getColor().equals(currentPawn.getColor()))
                    possibleMoves.add(new Move(startingTile, possibleEndingTile, color));
            }

        }


        return possibleMoves;
    }
}