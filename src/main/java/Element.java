import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element
{
    protected Position position;

    public Element(int xi, int yi)
    {
        position = new Position(xi, yi);
    }

    public Position posget()
    {
        return position;
    }

    public abstract void draw(TextGraphics graphics);

}
