package org.dimi.light;

import org.dimi.Vector3;

public class LightingProcessor {
    private double intensity;
    private final Vector3 point;
    private final Vector3 normal;

    public LightingProcessor(Vector3 point, Vector3 normal) {
        this.intensity = 0;
        this.point = point;
        this.normal = normal;
    }

    public double getIntensity() {
        return intensity;
    }

    void visit(AmbientLight light) {
        intensity += light.getIntensity();
    }

    void visit(DirectionalLight light) {
        Vector3 L = light.getDirection();
        double dot = normal.dot(L);
        if (dot > 0) {
            intensity += light.getIntensity() * dot / (normal.length() * L.length());
        }
    }

    void visit(PointLight light) {
        Vector3 L = light.getPosition().subtract(point);
        double dot = normal.dot(L);
        if (dot > 0) {
            intensity += light.getIntensity() * dot / (normal.length() * L.length());
        }
    }
}
