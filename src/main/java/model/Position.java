package model;

public record Position(int x, int y) {

    public boolean isEqualTo(Position other) {
        if (other == null) {
            return false;
        }
        return x == other.x && y == other.y;
    }
}
