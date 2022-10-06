import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero
{
    private Position position = new Position(10, 10);
    public Hero(int xi, int yi)
    {
        position.set_x(xi);
        position.set_y(yi);
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
    public void draw(Screen screen)
    {
        screen.setCharacter(position.get_x(), position.get_y(), TextCharacter.fromCharacter('X')[0]);
    }
    public void setPosition(Position position1)
    {
       position = position1;
    }
}
