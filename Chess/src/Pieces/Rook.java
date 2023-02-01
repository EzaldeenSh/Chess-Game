package Pieces;

import Behaviours.RookMovement;

import java.awt.*;

public class Rook extends Piece{
    public Rook(Color color){
        super(color, new RookMovement());
    }
    @Override
    public String toString() {
        return "Rook";
    }

}

