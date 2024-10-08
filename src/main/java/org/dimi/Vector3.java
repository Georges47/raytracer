package org.dimi;

public class Vector3 {
    private final double x;
    private final double y;
    private final double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 add(Vector3 other) {
        double x = this.x + other.x;
        double y = this.y + other.y;
        double z = this.z + other.z;
        return new Vector3(x, y, z);
    }

    public double length() {
        return Math.sqrt(dot(this));
    }

    public Vector3 normalize() {
        return new Vector3(x / length(), y / length(), z / length());
    }

    public Vector3 scale(double scale) {
        double x = this.x * scale;
        double y = this.y * scale;
        double z = this.z * scale;
        return new Vector3(x, y, z);
    }

    public Vector3 subtract(Vector3 other) {
        double x = this.x - other.x;
        double y = this.y - other.y;
        double z = this.z - other.z;
        return new Vector3(x, y, z);
    }

    public double dot(Vector3 other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
