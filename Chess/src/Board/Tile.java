package Board;
import Pieces.Piece;
public class Tile {
    private Piece piece;
    private final int tileXCoordinate;
    private final int tileYCoordinate;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return (int)(getTileXCoordinate() +1) +" "+ (int)(getTileYCoordinate()+1);
    }

    protected Tile(int tileXCoordinate, int tileYCoordinate, Piece piece){
        this.tileXCoordinate = tileXCoordinate;
        this.tileYCoordinate = tileYCoordinate;
        this.piece = piece;
    }
    public Piece getPiece(){
        return this.piece;
    }
    public int getTileXCoordinate(){
        return tileXCoordinate;
    }
    public int getTileYCoordinate(){
        return tileYCoordinate;
    }
    public void setPiece(Piece piece){
        this.piece = piece;
    }
    public void removePiece(){
        this.piece = null;
    }
    public boolean isTileEmpty(){
        return piece == null;
    }

}