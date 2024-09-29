package org.dimi;

public class Camera {
    private final Vector3 position;
    private final float[][] rotation;

    public Camera(Vector3 position, float[][] rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public Vector3 getPosition() {
        return position;
    }

    public float[][] getRotation() {
        return rotation;
    }
}
