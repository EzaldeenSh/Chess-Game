package Pieces;

import Behaviours.KnightMovement;

import java.awt.*;

public class Knight extends Piece{
    public Knight(Color color) {
        super(color, new KnightMovement());
    }
    @Override
    public String toString() {
        return "Knight";
    }
}
