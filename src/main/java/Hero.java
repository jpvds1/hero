import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element
{
    public int energy;
    public int score;
    public Hero(int xi, int yi)
    {
        super(xi, yi);
        energy = 3;
        score = 0;
    }
    public Position moveUp()
    {
        return new Position(position.get_x(), position.get_y() - 1);
    }
    public Position moveDown()
    {
        return new Position(position.get_x(), position.get_y() + 1);
    }
    public Position moveRight()
    {
        return new Position(position.get_x()+1, position.get_y());
    }
    public Position moveLeft()
    {
        return new Position(position.get_x() -1, position.get_y());
    }
    @Override
    public void draw(TextGraphics graphics)
    {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000785"));
        graphics.fillRectangle(new TerminalPosition(position.get_x(), position.get_y()), new TerminalSize(1, 1), ' ');
    }
    public void setPosition(Position position1)
    {
       position = position1;
    }
}
