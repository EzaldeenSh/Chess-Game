package Pieces;
import Behaviours.KingMovement;
import java.awt.*;
public class King extends Piece{
    private boolean isCastlingDone;
    public King(Color color){
       super(color, new KingMovement());
    }

    @Override
    public String toString() {
        return "King";
    }
}