package ChessGame;
import Board.ChessBoard;
import Board.Tile;
import Pieces.Piece;

import java.awt.*;

public class Move {
    Tile startingTile, endingTile;
     ChessBoard chessboard;
    Color color;
    Piece piece;
    public Move(Tile startingTile, Tile endingTile, Color color) {
        this.startingTile = startingTile;
        this.endingTile = endingTile;
       this.color = color;
        this.piece = startingTile.getPiece();
        chessboard = ChessBoard.getInstance();
    }


    @Override
    public boolean equals(Object obj) {
        Move currentMovement = (Move)obj;
        return(this.startingTile .equals( currentMovement.getStartingTile()) && endingTile .equals( currentMovement.getEndingTile()) && this.color.equals( currentMovement.getColor()));
    }


    public Tile getStartingTile() {
        return startingTile;
    }
    public Tile getEndingTile() {
        return endingTile;
    }
    public Color getColor(){
        return color;
    }
    public Piece getPiece() {
        return piece;
    }
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}