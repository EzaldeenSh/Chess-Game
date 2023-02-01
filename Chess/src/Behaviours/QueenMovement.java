package Behaviours;

import Board.ChessBoard;
import Board.Tile;
import ChessGame.Move;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QueenMovement implements MovementBehaviour {
    private List<Move> possibleMoves;
    private final BishopMovement diagonal;
    private final RookMovement horizontal;

    ChessBoard chessBoard;
    public QueenMovement(){
        diagonal = new BishopMovement();
        horizontal = new RookMovement();
        possibleMoves = new ArrayList<>();
        chessBoard = ChessBoard.getInstance();
    }

    @Override
    public List<Move> getPossibleMoves(Tile startingTile, Color color) {
        possibleMoves.clear();
        List<Move> list1 = diagonal.getPossibleMoves(startingTile, color);
        List<Move> list2 = horizontal.getPossibleMoves(startingTile, color);
        possibleMoves.addAll(list1);
        possibleMoves.addAll(list2);
        return possibleMoves;
    }
}
