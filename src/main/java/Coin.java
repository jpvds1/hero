import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element
{
    public Coin(int xi, int yi)
    {
        super(xi, yi);
    }

    @Override
    public void draw(TextGraphics graphics)
    {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.fillRectangle(new TerminalPosition(position.get_x(), position.get_y()), new TerminalSize(1, 1), ' ');
    }
}
