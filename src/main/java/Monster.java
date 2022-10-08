import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element
{
    Random random = new Random();
    public Monster(int xi, int yi)
    {
        super(xi, yi);
    }

    public Position move(Position position)
    {
        int r = random.nextInt(0,4);
        if(r == 0)
        {
            return moveUp();
        }
        else if(r == 1)
        {
            return moveDown();
        }
        else if(r == 2)
        {
            return moveLeft();
        }
        else if(r == 3)
        {
            return moveRight();
        }
        return moveUp();
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
        graphics.setBackgroundColor(TextColor.Factory.fromString("#00bf16"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#b80f0f"));
        graphics.putString(new TerminalPosition(position.get_x(), position.get_y()), "M");
    }
    public void setPosition(Position position1)
    {
        position = position1;
    }
}
