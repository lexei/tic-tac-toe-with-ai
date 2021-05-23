package me.lexei.tictactoe;

public class Position {

    private final int x;
    private final int y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        var otherPosition = (Position) o;
        return this.x == otherPosition.x && this.y == otherPosition.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
