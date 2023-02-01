package Pieces;
import Behaviours.MovementBehaviour;
import Board.Tile;
import ChessGame.Move;
import java.awt.*;
import java.util.List;
public abstract class Piece{
    private Color color;
    private MovementBehaviour movementBehaviour;
    public Piece(Color color, MovementBehaviour movementBehaviour){
        this.color = color;
        this.movementBehaviour = movementBehaviour;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    public List<Move> getPossibleMoves(Tile tile, Color color){
        return movementBehaviour.getPossibleMoves(tile, color);
    }
    public Color getColor(){
        return this.color;
    }
   }
