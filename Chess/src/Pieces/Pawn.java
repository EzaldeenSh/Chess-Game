package Pieces;

import Behaviours.PawnMovement;

import java.awt.*;

public class Pawn extends Piece{
    public Pawn(Color color){
        super(color, new PawnMovement());
    }
    @Override
    public String toString() {
        return "Pawn";
    }
}

