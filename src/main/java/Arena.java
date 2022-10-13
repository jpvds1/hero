import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena
{
    public int width;
    public int height;
    private Screen screen;
    public boolean z = true;
    public List<Wall> walls;
    public List<Coin> coins;
    public List<Monster> monsters;
    public boolean DEATH = false;
    public TextGraphics graphics;
    public boolean WIN = false;
    public int MonsterNumber = 15;
    public int CoinNumber = 10;
    public int CoinSize;
    public boolean partial = false;
    Position door;

    Hero hero = new Hero(10, 10);
    public Arena(int x1, int x2)
    {
        width = x1;
        height = x2;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonster();
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
    public void moveHero(Position position) throws IOException
    {
        if(canHeroMove(position))
        {
            for(Monster monster : monsters)
            {
                Position position1 = monster.move(monster.posget());
                if(canMonsterMove(position1))
                {
                    monster.setPosition(position1);
                }
            }
            if (verifyMonsterCollisions(position))
            {
                if(hero.energy != 1)
                {
                    hero.energy--;
                }
                else
                {
                    DEATH = true;
                }
            }
            hero.setPosition(position);
        }
    }

    public boolean verifyMonsterCollisions(Position position)
    {
        for (Monster monster : monsters)
        {
            if(monster.posget().equals(position))
            {
                return true;
            }
        }
        return false;
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
        else if (key.getKeyType() == KeyType.EOF)
        {
            z = false;
        }
    }
    private boolean canHeroMove(Position position) throws IOException
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
            for(Monster monster : monsters)
            {
                if(monster.posget().equals(position))
                {
                    if(hero.energy != 1)
                    {
                        hero.energy--;
                    }
                    else
                    {
                        DEATH = true;
                    }
                    return false;
                }
            }
            for (Coin coin : coins)
            {
                if(coin.posget().equals(position))
                {
                    coins.remove(coin);
                    hero.score++;
                    if(hero.score == CoinSize)
                    {
                        partial = true;
                        doorgenerator();
                    }
                    break;
                }
            }
            if(partial)
            {
                if(position.equals(door))
                {
                    WIN = true;
                }
            }
            return true;
        }
        return false;
    }
    private void doorgenerator()
    {
        Random random = new Random();
        int r = random.nextInt(2,height);
        int r2 = random.nextInt(2, width);
        door = new Position(r2, r);
    }
    private boolean canMonsterMove(Position position)
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
            for(Monster monster : monsters)
            {
                if(monster.posget().equals(position))
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
        if(!DEATH)
        {
            if(WIN)
            {
                graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
                graphics.setForegroundColor(TextColor.Factory.fromString("#00bf16"));
                graphics.enableModifiers(SGR.BOLD);
                graphics.putString(new TerminalPosition(width / 2 - 2, height / 2), "Yay :)");
                graphics.putString(new TerminalPosition(0, height - 2), "Press Q to leave");
                graphics.putString(new TerminalPosition(0, height - 1), "or R to restart");
            }
            else if(partial)
            {
                graphics.setBackgroundColor(TextColor.Factory.fromString("#00bf16"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
                graphics.setBackgroundColor(TextColor.Factory.fromString("#ffffff"));
                graphics.fillRectangle(new TerminalPosition(door.get_x(), door.get_y()), new TerminalSize(1, 1), ' ');
                hero.draw(graphics);
                for (Wall wall : walls)
                    wall.draw(graphics);
                for(Coin coin : coins)
                    coin.draw(graphics);
                for(Monster monster : monsters)
                    monster.draw(graphics);
                graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics.setForegroundColor(TextColor.Factory.fromString("#b80f0f"));
                graphics.putString(new TerminalPosition(1, 0), "Energy = " + hero.energy);
                graphics.putString(new TerminalPosition(1, height - 1), "Score = " + hero.score);
            }
            else
            {
                graphics.setBackgroundColor(TextColor.Factory.fromString("#00bf16"));
                graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
                hero.draw(graphics);
                for (Wall wall : walls)
                    wall.draw(graphics);
                for(Coin coin : coins)
                    coin.draw(graphics);
                for(Monster monster : monsters)
                    monster.draw(graphics);
                graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
                graphics.setForegroundColor(TextColor.Factory.fromString("#b80f0f"));
                graphics.putString(new TerminalPosition(1, 0), "Energy = " + hero.energy);
                graphics.putString(new TerminalPosition(1, height - 1), "Score = " + hero.score);
            }
        }
        else
        {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#000000"));
            graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
            graphics.setForegroundColor(TextColor.Factory.fromString("#b80f0f"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(width / 2 - 8, height / 2), "You got Deaded");
            graphics.putString(new TerminalPosition(0, height - 2), "Press Q to leave");
            graphics.putString(new TerminalPosition(0, height - 1), "or R to restart");
        }
    }
    private List<Coin> createCoins()
    {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < CoinNumber; i++)
        {
            Position p = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (p.equals(hero.posget()))
            {
                i--;
                continue;
            }
            boolean z = true;
            for (Coin c : coins)
            {
                if (p.equals(c.posget()))
                {
                    i--;
                    z = false;
                    break;
                }
            }
            if(z)
            {
                coins.add(new Coin(p.get_x(), p.get_y()));
            }
        }
        return coins;
    }
    private List<Monster> createMonster()
    {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < MonsterNumber; i++)
        {
            Position p = new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (p.equals(hero.posget()))
            {
                i--;
                continue;
            }
            boolean z = true;
            for (Coin c : coins)
            {
                if (p.equals(c.posget()))
                {
                    i--;
                    z = false;
                    break;
                }
            }
            for (Monster monster : monsters)
            {
                if (p.equals(monster.posget()))
                {
                    i--;
                    z = false;
                    break;
                }
            }
            if(z)
            {
                monsters.add(new Monster(p.get_x(), p.get_y()));
            }
        }
        CoinSize = coins.size();
        return monsters;
    }

}
