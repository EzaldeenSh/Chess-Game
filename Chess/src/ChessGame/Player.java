package ChessGame;
import java.awt.*;
public class Player {
    private final String name;
    private final Color color;
    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
    public Player(String name, Color color){
        this.name = name;
        this.color = color;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}