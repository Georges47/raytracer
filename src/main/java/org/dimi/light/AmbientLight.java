package org.dimi.light;

public class AmbientLight implements Light {
    private final double intensity;

    public AmbientLight(double intensity) {
        this.intensity = intensity;
    }

    @Override
    public double getIntensity() {
        return intensity;
    }

    @Override
    public void accept(LightingProcessor lightingProcessor) {
        lightingProcessor.visit(this);
    }
}
