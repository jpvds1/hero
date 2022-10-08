import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena
{
    private int width;
    private int height;
    private Screen screen;
    public boolean z = true;
    private List<Wall> walls;

    Hero hero = new Hero(10, 10);
    public Arena(int x1, int x2)
    {
        width = x1;
        height = x2;
        this.walls = createWalls();

    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    public void moveHero(Position position)
    {
        if(canHeroMove(position) == true)
        {
            hero.setPosition(position);
        }
    }
    public void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException
    {
        // possibly case to switch case
        if (key.getKeyType() == KeyType.ArrowUp)
        {
            moveHero(hero.moveUp());
        }
        else if (key.getKeyType() == KeyType.ArrowDown)
        {
            moveHero(hero.moveDown());
        }
        else if (key.getKeyType() == KeyType.ArrowRight)
        {
            moveHero(hero.moveRight());
        }
        else if (key.getKeyType() == KeyType.ArrowLeft)
        {
            moveHero(hero.moveLeft());
        }
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
        {
            screen.close();
        }
        else if (key.getKeyType() == KeyType.EOF)
        {
            z = false;
        }
    }
    private boolean canHeroMove(Position position)
    {
        if(position.get_x() <= width && position.get_y() <= height)
        {
            for (Wall wall : walls)
            {
                if(wall.posget().equals(position))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void draw(TextGraphics graphics) throws IOException
    {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
    }

}
