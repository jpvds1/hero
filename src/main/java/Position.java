public class Position
{
    private int x;
    private int y;
    public Position(int xi, int yi)
    {
        x = xi;
        y = yi;
    }
    public int get_x()
    {
        return x;
    }
    public int get_y()
    {
        return y;
    }
    public void set_x(int xi)
    {
        x = xi;
    }
    public void set_y(int yi)
    {
        y = yi;
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.get_x() && y == p.get_y();
    }
}
