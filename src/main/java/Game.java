import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game
{
    Arena arena = new Arena(40,20);
    private Screen screen;
    public boolean z = true;


    public Game()
    {
        try
        {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen();           // screens must be started
            screen.doResizeIfNecessary();   // resize screen if necessary

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void moveHero(Position position)
    {
        hero.setPosition(position);
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException
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
    private void draw() throws IOException
    {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    public void run() throws IOException
    {
        while(z)
        {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
        }
    }
}
