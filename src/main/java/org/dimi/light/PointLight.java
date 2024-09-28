package org.dimi.light;

import org.dimi.Vector3;

public class PointLight implements Light {
    private final double intensity;
    private final Vector3 position;

    public PointLight(double intensity, Vector3 position) {
        this.intensity = intensity;
        this.position = position;
    }

    @Override
    public double getIntensity() {
        return intensity;
    }

    @Override
    public void accept(LightProcessor lightProcessor) {
        lightProcessor.visit(this);
    }

    public Vector3 getPosition() {
        return position;
    }
}
