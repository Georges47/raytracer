package org.dimi;

public class Sphere {
    private final Vector3 center;
    private final double radius;
    private final int color;

    public Sphere(Vector3 center, double radius, int color) {
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

    public int getColor() {
        return color;
    }

}
