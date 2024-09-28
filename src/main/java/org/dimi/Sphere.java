package org.dimi;

import java.awt.*;

public class Sphere {
    private final Vector3 center;
    private final double radius;
    private final Color color;

    public Sphere(Vector3 center, double radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Vector3 getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

}
