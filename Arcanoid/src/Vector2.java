class Vector2 {
    float x;
    float y;

    Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    static Vector2 mul(Vector2 a, float v) {
        return new Vector2(a.x * v, a.y * v);
    }

    static Vector2 add(Vector2 a, Vector2 b) {
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    private float distance() {
        return (float) (Math.sqrt(x * x + y * y));
    }

    void normalize() {
        float dist = distance();
        this.x /= dist;
        this.y /= dist;
    }
}

