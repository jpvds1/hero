import com.googlecode.lanterna.TerminalSize;
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




    public Game()
    {
        try
        {
            TerminalSize terminalSize = new TerminalSize(arena.width, arena.height);
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

    private void processKey(KeyStroke key) throws IOException
    {
        arena.processKey(key);
    }
    private void draw() throws IOException
    {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException
    {
        while(arena.z)
        {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'))
            {
                screen.close();
            }
            else if ((key.getKeyType() == KeyType.Character && key.getCharacter() == 'r'))
            {
                arena = new Arena(40,20);
            }
        }
    }
}
