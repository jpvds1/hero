import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element
{

    public Hero(int xi, int yi)
    {
        super(xi, yi);
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
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), "H");
    }
    public void setPosition(Position position1)
    {
       position = position1;
    }
}
