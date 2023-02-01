package Pieces;
import Behaviours.BishopMovement;
import java.awt.*;
public class Bishop extends Piece{
    public Bishop(Color color){
       super(color, new BishopMovement());
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
