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
    private Screen screen;
    private int x = 10;
    private int y = 10;
    private boolean z = true;
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

            screen.clear();
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException
    {
        // possibly case to switch case
        if (key.getKeyType() == KeyType.ArrowUp)
        {
            System.out.println(key);
            y -= 1;
        }
        else if (key.getKeyType() == KeyType.ArrowDown)
        {
            System.out.println(key);
            y += 1;
        }
        else if (key.getKeyType() == KeyType.ArrowRight)
        {
            System.out.println(key);
            x += 1;
        }
        else if (key.getKeyType() == KeyType.ArrowLeft)
        {
            System.out.println(key);
            x -= 1;
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
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
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
