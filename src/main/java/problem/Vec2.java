package problem;

import static java.lang.Math.sqrt;

public class Vec2 {
    double x,y;

    public Vec2() {
        x = 0;
        y = 0;
    }

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(x + other.x, y + other.y);
    }

    public Vec2 add(double other) {
        return new Vec2(x + other, y + other);
    }

    public Vec2 sub(Vec2 other) {
        return new Vec2(x - other.x, y - other.y);
    }

    public Vec2 sub(double other) {
        return new Vec2(x - other, y - other);
    }

    public Vec2 scale(double factor) {
        return new Vec2(x * factor, y * factor);
    }

    public double sizeSq()
    {
        return x * x + y * y;
    }

    public double size()
    {
        return sqrt(sizeSq());
    }

    @Override
    public String toString() {
        return "Vec2(" + x + ", " + y + ")";
    }
}
