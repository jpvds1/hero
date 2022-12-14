import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
public class Wall extends Element
{

    public Wall(int xi, int yi)
    {
        super(xi, yi);
    }
    @Override
    public void draw(TextGraphics graphics)
    {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        graphics.fillRectangle(new TerminalPosition(position.get_x(), position.get_y()), new TerminalSize(1, 1), ' ');

    }

    public Position posget()
    {
        return position;
    }
}
