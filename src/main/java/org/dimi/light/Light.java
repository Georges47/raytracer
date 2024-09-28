package org.dimi.light;

public interface Light {
    double getIntensity();
    void accept(LightProcessor lightProcessor);
}
