package org.dimi;

import org.dimi.light.Light;

public class Scene {
    private final Camera camera;
    private final int viewportWidth;
    private final int viewportHeight;
    private final double cameraToViewportDistance;
    private final int canvasWidth;
    private final int canvasHeight;
    private final Sphere[] spheres;
    private final Light[] lights;

    public Scene(Camera camera, Sphere[] spheres, Light[] lights) {
        this.camera = camera;
        this.viewportWidth = 1;
        this.viewportHeight = 1;
        this.cameraToViewportDistance = 1;
        this.canvasWidth = 900;
        this.canvasHeight = 900;
        this.spheres = spheres;
        this.lights = lights;
    }

    public Camera getCamera() {
        return camera;
    }

    public int getViewportWidth() {
        return viewportWidth;
    }

    public int getViewportHeight() {
        return viewportHeight;
    }

    public double getCameraToViewportDistance() {
        return cameraToViewportDistance;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public Sphere[] getSpheres() {
        return spheres;
    }

    public void updateSphereX(int i, double x) {
        Sphere sphere = spheres[i];
        spheres[i] = new Sphere(new Vector3(x, sphere.getCenter().getY(), sphere.getCenter().getZ()), sphere.getRadius(), sphere.getColor());
    }

    public void updateSphereY(int i, double y) {
        Sphere sphere = spheres[i];
        spheres[i] = new Sphere(new Vector3(sphere.getCenter().getX(), y, sphere.getCenter().getZ()), sphere.getRadius(), sphere.getColor());
    }

    public void updateSphereZ(int i, double z) {
        Sphere sphere = spheres[i];
        spheres[i] = new Sphere(new Vector3(sphere.getCenter().getX(), sphere.getCenter().getY(), z), sphere.getRadius(), sphere.getColor());
    }
    public Light[] getLights() {
        return lights;
    }
}
