package Behaviours;

import Board.Tile;
import ChessGame.Move;

import java.awt.*;
import java.util.List;

public interface MovementBehaviour {
     List<Move> getPossibleMoves(Tile tile, Color color);
}
