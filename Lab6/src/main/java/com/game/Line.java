package com.game;

import java.awt.*;
import java.io.Serializable;

public class Line implements Serializable {
    private static final long serialVersionUID = 1L;
    private Dot dot1;
    private Dot dot2;
    private Color color;

    public Line(Dot dot1, Dot dot2, Color color) {
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.color = color;
    }

    public Dot getDot1() { return dot1; }
    public Dot getDot2() { return dot2; }
    public Color getColor() { return color; }

    public double length() {
        return dot1.distance(dot2);
    }
}