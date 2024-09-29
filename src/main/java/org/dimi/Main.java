package org.dimi;

import org.dimi.light.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static Scene scene = new Scene(
        new Camera(
            new Vector3(3, 0, 1),
            new float[][]{
                {0.7071f, 0f, -0.7071f},
                {0f, 1f, 0f},
                {0.7071f, 0f, 0.7071f}
            }
        ),
        new Sphere[]{
            new Sphere(new Vector3(0, -1, 3), 1, Color.RED),
            new Sphere(new Vector3(2, 0, 4), 1, Color.GREEN),
            new Sphere(new Vector3(-2, 0, 4), 1, Color.BLUE),
            new Sphere(new Vector3(0, -5001, 0), 5000, Color.YELLOW),
        },
        new Light[]{
            new AmbientLight(0.2f),
            new PointLight(0.6f, new Vector3(2, 1, 0)),
            new DirectionalLight(0.2f, new Vector3(1, 4, 4))
        }
    );
    public static int BACKGROUND_COLOR = Color.BLACK.getRGB();

    private static BufferedImage image;
    private static JPanel jPanel;

    private static final MathProcessor mathProcessor = new MathProcessor();

    private static JPanel objectSection(String label, int objectIndex) {
        JTextField xField = new JTextField(3);
        JTextField yField = new JTextField(3);
        JTextField zField = new JTextField(3);

        Vector3 center = scene.getSpheres()[objectIndex].getCenter();
        xField.setText(String.valueOf(center.getX()));
        yField.setText(String.valueOf(center.getY()));
        zField.setText(String.valueOf(center.getZ()));

        xField.getDocument().addDocumentListener(new AxisDocumentListener(Axis.X, xField, objectIndex, scene, jPanel));
        yField.getDocument().addDocumentListener(new AxisDocumentListener(Axis.Y, yField, objectIndex, scene, jPanel));
        zField.getDocument().addDocumentListener(new AxisDocumentListener(Axis.Z, zField, objectIndex, scene, jPanel));

        JPanel section = new JPanel();
        section.add(new JLabel(label));
        section.add(xField);
        section.add(yField);
        section.add(zField);
        return section;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(scene.getCanvasWidth(), scene.getCanvasHeight());

        image = new BufferedImage(scene.getCanvasWidth(), scene.getCanvasHeight(), BufferedImage.TYPE_INT_ARGB);

        renderSpheres();

        jPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        JPanel redSphereSection = objectSection("RED    ", 0);
        JPanel greenSphereSection = objectSection("GREEN ", 1);
        JPanel blueSphereSection = objectSection("BLUE  ", 2);

        JPanel controlPanel = new JPanel(new GridLayout(4,1));
        controlPanel.add(redSphereSection);
        controlPanel.add(greenSphereSection);
        controlPanel.add(blueSphereSection);

        frame.add(jPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    static void renderSpheres() {
        for (int y = -scene.getCanvasHeight() / 2; y < scene.getCanvasHeight() / 2; y++) {
            for (int x = -scene.getCanvasWidth() / 2; x < scene.getCanvasWidth() / 2; x++) {
                Vector3 direction = mathProcessor.matrixVectorMultiplication(scene.getCamera().getRotation(), canvasToViewport(x, y));
                int color = traceRay(scene.getCamera().getPosition(), direction, 1, Integer.MAX_VALUE);
                image.setRGB(screenX(x), screenY(y), color);
            }
        }
    }

    //
    private static Vector3 canvasToViewport(int canvasX, int canvasY) {
        return new Vector3(viewportX(canvasX), viewportY(canvasY), scene.getCameraToViewportDistance());
    }

    private static double viewportX(int canvasX) {
        return (double) (canvasX * scene.getViewportWidth()) / scene.getCanvasWidth();
    }

    private static double viewportY(int canvasY) {
        return (double) (canvasY * scene.getViewportHeight()) / scene.getCanvasHeight();
    }

    //
    private static int traceRay(Vector3 rayOrigin, Vector3 rayDirection, int tMin, int tMax) {
        double closestT = Integer.MAX_VALUE;
        Sphere closestSphere = null;
        for (Sphere sphere : scene.getSpheres()) {
            double[] ts = intersectRaySphere(rayOrigin, rayDirection, sphere);
            if (ts[0] >= tMin && ts[0] <= tMax && ts[0] < closestT) {
                closestT = ts[0];
                closestSphere = sphere;
            }
            if (ts[1] >= tMin && ts[1] <= tMax && ts[1] < closestT) {
                closestT = ts[1];
                closestSphere = sphere;
            }
        }
        if (closestSphere == null) return BACKGROUND_COLOR;
        Vector3 point = scene.getCamera().getPosition().add(rayDirection.scale(closestT));
        Vector3 normal = point.subtract(closestSphere.getCenter()).normalize();

        double intensity = computeLighting(point, normal);
        int red = closestSphere.getColor().getRed();
        int green = closestSphere.getColor().getGreen();
        int blue = closestSphere.getColor().getBlue();
        return new Color((float) (red * intensity/255), (float) (green * intensity/255), (float) (blue * intensity/255)).getRGB();
    }

    private static double[] intersectRaySphere(Vector3 rayOrigin, Vector3 rayDirection, Sphere sphere) {
        double r = sphere.getRadius();
        Vector3 CO = rayOrigin.subtract(sphere.getCenter());

        double a = rayDirection.dot(rayDirection);
        double b = 2 * CO.dot(rayDirection);
        double c = CO.dot(CO) - r * r;

        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new double[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }

        double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        return new double[]{t1, t2};
    }

    //
    private static int screenX(int canvasX) {
        return scene.getCanvasWidth() / 2 + canvasX;
    }

    private static int screenY(int canvasY) {
        return scene.getCanvasHeight() / 2 - canvasY - 1; // TODO. Que hago con ese -1 ?
    }

    // Lighting
    private static double computeLighting(Vector3 point, Vector3 normal) {
        LightProcessor lightProcessor = new LightProcessor(point, normal);
        for (Light light : scene.getLights()) {
            light.accept(lightProcessor);
        }
        return lightProcessor.getIntensity();
    }
}