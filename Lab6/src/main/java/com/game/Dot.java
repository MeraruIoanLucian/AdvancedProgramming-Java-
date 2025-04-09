package com.game;

import java.awt.*;
import java.io.Serializable;

public class Dot implements Serializable {
    private static final long serialVersionUID = 1L;
    private int x;
    private int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public double distance(Dot other) {
        return Math.hypot(x - other.x, y - other.y);
    }

    public boolean containsPoint(Point p) {
        int radius = 5;
        return p.distance(x, y) <= radius;
    }
}