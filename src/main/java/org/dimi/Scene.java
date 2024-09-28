package org.dimi;

import org.dimi.light.Light;

public class Scene {
    private final Sphere[] spheres;
    private final Light[] lights;

    public Scene(Sphere[] spheres, Light[] lights) {
        this.spheres = spheres;
        this.lights = lights;
    }

    public Sphere[] getSpheres() {
        return spheres;
    }

    public void updateSphere(int i, Sphere sphere) {
        spheres[i] = sphere;
    }

    public Light[] getLights() {
        return lights;
    }
}
