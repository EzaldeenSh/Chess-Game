package Pieces;
import Behaviours.QueenMovement;
import java.awt.*;
public class Queen extends Piece{
    public Queen(Color color){
        super(color, new QueenMovement());
    }
    @Override
    public String toString() {
        return "Queen";
    }
}
