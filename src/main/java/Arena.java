import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.io.IOException;

public class Arena
{
    private int width;
    private int height;
    private Screen screen;
    private boolean z = true;
    Hero hero = new Hero(10, 10);
    public Arena(int x1, int x2)
    {
        width = x1;
        height = x2;
    }

    private void moveHero(Position position)
    {
        hero.setPosition(position);
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

    public void draw(Screen screen) throws IOException
    {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    private void processKey(KeyStroke key)
    {

    }
}
