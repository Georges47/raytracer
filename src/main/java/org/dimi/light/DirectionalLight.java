package org.dimi.light;

import org.dimi.Vector3;

public class DirectionalLight implements Light {
    private final double intensity;
    private final Vector3 direction;

    public DirectionalLight(double intensity, Vector3 direction) {
        this.intensity = intensity;
        this.direction = direction;
    }

    @Override
    public double getIntensity() {
        return intensity;
    }

    @Override
    public void accept(LightingProcessor lightingProcessor) {
        lightingProcessor.visit(this);
    }

    public Vector3 getDirection() {
        return direction;
    }
}
