package Pieces;

import java.awt.*;

public class PiecesFactory {
    public Piece createPiece(String pieceType, Color color){
        if(pieceType == null || color == null)
            throw new IllegalArgumentException();
        if(pieceType.equalsIgnoreCase("Queen"))
            return new Queen(color);
        else if(pieceType.equalsIgnoreCase("King"))
            return new King(color);
        else if(pieceType.equalsIgnoreCase("Rook"))
            return new Rook(color);
        else if(pieceType.equalsIgnoreCase("Knight"))
            return new Knight(color);
        else if(pieceType.equalsIgnoreCase("Bishop"))
            return new Bishop(color);
        else if(pieceType.equalsIgnoreCase("Pawn"))
            return new Pawn(color);
        return null;
    }
}
